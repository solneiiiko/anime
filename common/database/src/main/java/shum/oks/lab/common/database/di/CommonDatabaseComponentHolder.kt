/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.database.di

import shum.oks.lab.core.di.BaseComponentHolder

object CommonDatabaseComponentHolder : BaseComponentHolder<
    CommonDatabaseApi,
    CommonDatabaseDependencies
>() {

    override fun buildComponent(dependencies: CommonDatabaseDependencies): CommonDatabaseApi =
        DaggerCommonDatabaseComponent.builder()
            .commonDatabaseDependencies(dependencies)
            .build()
}