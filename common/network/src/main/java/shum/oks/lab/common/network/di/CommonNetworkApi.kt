/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.network.di

import retrofit2.Retrofit
import shum.oks.lab.core.di.BaseApi

interface CommonNetworkApi : BaseApi {

    fun getRetrofit(): Retrofit
}
