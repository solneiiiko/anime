/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.network

import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseRemoteDataSource {

    // TODO -> to CallAdapter
    // TODO Interceptor for auth (try extra url! with token)
    protected suspend fun <T> safeApiCall(
        call: suspend () -> T,
    ): ApiResult<T> = try {
        ApiResult.Success(call())
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        val exception = when (e.code()) {
            // TODO 401 ??
            in 500..599 -> ApiServerException(e.message())
            else -> ApiUnknownException(e.message ?: UNKNOWN_ERROR_MESSAGE)
        }
        ApiResult.Failure(exception)
    } catch (e: IOException) {
        ApiResult.Failure(ApiNetworkException(e.message ?: UNKNOWN_ERROR_MESSAGE, e))
    } catch (e: Exception) {
        ApiResult.Failure(ApiUnknownException(e.message ?: UNKNOWN_ERROR_MESSAGE))
    }

    companion object {
        private const val UNKNOWN_ERROR_MESSAGE = "Unknown error"
    }
}
