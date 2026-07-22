/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.network.calladapters

import retrofit2.Call
import retrofit2.CallAdapter
import shum.oks.lab.core.network.ApiResult
import java.lang.reflect.Type

internal class ApiResultCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Call<ApiResult<T>>> {

    override fun responseType(): Type =
        responseType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> =
        ApiResultCall(call)
}
