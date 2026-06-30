/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.database.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        CommonDatabaseDependencies::class
    ],
    modules = [
        CommonDatabaseModule::class,
    ]
)
internal interface CommonDatabaseComponent : CommonDatabaseApi