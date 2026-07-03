/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di

import shum.oks.lab.common.database.di.CommonDatabaseComponentHolder
import shum.oks.lab.common.database.di.CommonDatabaseDependencies
import shum.oks.lab.common.network.di.CommonNetworkComponentHolder
import shum.oks.lab.common.network.di.CommonNetworkDependencies
import shum.oks.lab.core.di.DependenciesProvider
import javax.inject.Inject

class ComponentHolderInitializer @Inject constructor(
    private val commonDatabaseDependenciesProvider: DependenciesProvider<CommonDatabaseDependencies>,
    private val commonNetworkDependenciesProvider: DependenciesProvider<CommonNetworkDependencies>,
) {

    fun init() {
        initCommon()
        initFeatures()
    }

    private fun initCommon() {
        CommonDatabaseComponentHolder.init(commonDatabaseDependenciesProvider)
        CommonNetworkComponentHolder.init(commonNetworkDependenciesProvider)
    }

    private fun initFeatures() {
        // TODO
    }
}