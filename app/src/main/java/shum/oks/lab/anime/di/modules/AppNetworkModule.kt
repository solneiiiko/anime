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
import shum.oks.lab.anime.BuildConfig
import shum.oks.lab.anime.config.Environment
import shum.oks.lab.common.network.NetworkConfig
import shum.oks.lab.common.network.di.CommonNetworkDependencies
import shum.oks.lab.core.di.DependenciesProvider

@Module
internal class AppNetworkModule {

    @Provides
    fun provideCommonNetworkDependencies(
        environment: Environment,
    ): DependenciesProvider<CommonNetworkDependencies> = {
        object : CommonNetworkDependencies {

            override val networkConfig: NetworkConfig
                get() = NetworkConfig(
                    myAnimeListClientId = BuildConfig.MY_ANIME_LIST_CLIENT_ID,
                    loggingLevel = if (environment.networkLoggingEnabled)
                        NetworkConfig.LoggingLevel.BODY
                    else
                        NetworkConfig.LoggingLevel.NONE
                )
        }
    }
}
