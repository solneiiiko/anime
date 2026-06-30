/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.network.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        CommonNetworkDependencies::class,
    ],
    modules = [
        CommonNetworkModule::class,
    ]
)
internal interface CommonNetworkComponent : CommonNetworkApi
