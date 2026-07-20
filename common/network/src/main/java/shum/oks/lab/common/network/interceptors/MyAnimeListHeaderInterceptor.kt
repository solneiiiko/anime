/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import shum.oks.lab.common.network.NetworkConfig
import shum.oks.lab.common.network.qualifiers.MyAnimeListNetwork
import javax.inject.Inject

@MyAnimeListNetwork
internal class MyAnimeListHeaderInterceptor @Inject constructor(
    private val networkConfig: NetworkConfig,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(CLIENT_ID_HEADER_NAME, networkConfig.myAnimeListClientId ?: "")
            .build()
        return chain.proceed(newRequest)
    }

    private companion object {
        const val CLIENT_ID_HEADER_NAME = "X-MAL-CLIENT"
    }
}
