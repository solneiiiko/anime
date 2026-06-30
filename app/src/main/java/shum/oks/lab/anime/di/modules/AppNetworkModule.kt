/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di.modules

import dagger.Module
import dagger.Provides
import shum.oks.lab.anime.Environment
import shum.oks.lab.common.network.NetworkConfig
import shum.oks.lab.common.network.di.CommonNetworkDependencies

@Module
class AppNetworkModule {

    @Provides
    fun provideCommonNetworkDependencies(): CommonNetworkDependencies =
        object : CommonNetworkDependencies {

            override val networkConfig: NetworkConfig
                get() = NetworkConfig(
                    baseUrl = BASE_URL,
                    loggingLevel = if (Environment.ENABLE_LOGGING)
                        NetworkConfig.LoggingLevel.BODY
                    else
                        NetworkConfig.LoggingLevel.NONE
                )
        }

    private companion object {
        const val BASE_URL = "https://api.jikan.moe/"
    }
}
