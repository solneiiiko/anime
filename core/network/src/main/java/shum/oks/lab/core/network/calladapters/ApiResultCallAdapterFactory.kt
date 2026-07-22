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
import retrofit2.Retrofit
import shum.oks.lab.core.network.ApiResult
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation?>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java)
            return null

        val parameterizedReturnType = returnType as? ParameterizedType ?: return null
        val callType = getParameterUpperBound(0, parameterizedReturnType)

        if (getRawType(callType) != ApiResult::class.java)
            return null

        val parameterizedCallType = callType as? ParameterizedType ?: return null
        val bodyType = getParameterUpperBound(0, parameterizedCallType)

        return ApiResultCallAdapter<Any>(bodyType)
    }
}
