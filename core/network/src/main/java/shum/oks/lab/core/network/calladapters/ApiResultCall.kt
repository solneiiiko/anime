/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.network.calladapters

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import shum.oks.lab.core.network.ApiClientException
import shum.oks.lab.core.network.ApiException
import shum.oks.lab.core.network.ApiNetworkException
import shum.oks.lab.core.network.ApiResult
import shum.oks.lab.core.network.ApiServerException
import shum.oks.lab.core.network.ApiUnknownException
import java.io.IOException

internal class ApiResultCall<T>(
    private val delegate: Call<T>,
) : Call<ApiResult<T>>  {

    override fun execute(): Response<ApiResult<T>> = try {
        Response.success(delegate.execute().toApiResult())
    } catch (e: IOException) {
        Response.success(e.toApiResult())
    }

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(
                    this@ApiResultCall,
                    Response.success(response.toApiResult()))
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(
                    this@ApiResultCall,
                    Response.success(t.toApiResult())
                )
            }
        })
    }

    override fun isExecuted(): Boolean =
        delegate.isExecuted

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean =
        delegate.isCanceled

    override fun clone(): Call<ApiResult<T>> =
        ApiResultCall(delegate.clone())

    override fun request(): Request =
        delegate.request()

    override fun timeout(): Timeout =
        delegate.timeout()
}

private fun <T> Throwable.toApiResult(): ApiResult<T> = when (this) {
    is IOException -> ApiResult.Failure(ApiNetworkException(message ?: UNKNOWN_ERROR_MESSAGE, this))
    is HttpException -> {
        ApiResult.Failure(
            exception = getApiExceptionByCode(
                code = code(),
                message = message ?: UNKNOWN_ERROR_MESSAGE,
            )
        )
    }
    else -> ApiResult.Failure(ApiUnknownException(message ?: UNKNOWN_ERROR_MESSAGE))
}

private fun <T> Response<T>.toApiResult(): ApiResult<T> =
    if (isSuccessful) {
        val body = body()
        if (body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Failure(ApiUnknownException(RESPONSE_BODY_NULL_MESSAGE))
        }
    } else {
        ApiResult.Failure(
            exception = getApiExceptionByCode(
                code = code(),
                message = message() ?: UNKNOWN_ERROR_MESSAGE,
            )
        )
    }

/**
 * Only standard HTTP status codes are handled here.
 * Custom protocol-specific codes (e.g. SIP 600 Busy Everywhere or
 * vendor-specific 600 Invalid Headers) are intentionally ignored,
 * as their handling is defined by the client-server contract.
 * These APIs do not use such custom status codes.
 */
private fun getApiExceptionByCode(code: Int, message: String): ApiException {
    return when (code) {
        in 400..499 -> ApiClientException(message, code.toApiClientExceptionCode())
        in 500..599 -> ApiServerException(message)
        else -> ApiUnknownException(
            UNKNOWN_ERROR_MESSAGE,
            IllegalArgumentException("Exception for code $code is not defined >_<") // TODO + event in Analytics
        )
    }
}

private fun Int.toApiClientExceptionCode(): ApiClientException.Code = when (this) {
    400 -> ApiClientException.Code.BadRequest
    401 -> ApiClientException.Code.Unauthorized
    403 -> ApiClientException.Code.Forbidden
    404 -> ApiClientException.Code.NotFound
    409 -> ApiClientException.Code.Conflict
    429 -> ApiClientException.Code.TooManyRequests
    else -> ApiClientException.Code.Unknown
}

private const val UNKNOWN_ERROR_MESSAGE = "Unknown error"
private const val RESPONSE_BODY_NULL_MESSAGE = "Response body is null"
