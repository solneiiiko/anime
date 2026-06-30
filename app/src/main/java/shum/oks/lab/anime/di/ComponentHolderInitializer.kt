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
import javax.inject.Inject
import javax.inject.Provider

class ComponentHolderInitializer @Inject constructor(
    private val commonDatabaseDependenciesProvider: Provider<CommonDatabaseDependencies>,
) {

    fun init() {
        initCommon()
        initFeatures()
    }

    private fun initCommon() {
        CommonDatabaseComponentHolder.init(commonDatabaseDependenciesProvider)
    }

    private fun initFeatures() {
        // TODO
    }
}