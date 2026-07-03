/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.network.di

import shum.oks.lab.core.di.BaseComponentHolder

object CommonNetworkComponentHolder : BaseComponentHolder<
    CommonNetworkApi,
    CommonNetworkDependencies,
>() {

    override fun buildComponent(dependencies: CommonNetworkDependencies): CommonNetworkApi =
        DaggerCommonNetworkComponent.builder()
            .commonNetworkDependencies(dependencies)
            .build()
}
