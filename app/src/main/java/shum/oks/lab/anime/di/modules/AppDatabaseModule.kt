/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import shum.oks.lab.common.database.di.CommonDatabaseApi
import shum.oks.lab.common.database.di.CommonDatabaseComponentHolder
import shum.oks.lab.common.database.di.CommonDatabaseDependencies

@Module
internal class AppDatabaseModule {

    @Provides
    fun provideCommonDatabaseDependencies(
        appContext: Context,
    ): CommonDatabaseDependencies =
        object : CommonDatabaseDependencies {
            override val appContext = appContext
        }

    @Provides
    fun provideCommonDatabaseApi(): CommonDatabaseApi =
        CommonDatabaseComponentHolder.get()
}