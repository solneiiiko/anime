/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.database.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import shum.oks.lab.anime.list.data.api.AnimeListDatabaseDelegate
import shum.oks.lab.common.database.AppDatabase
import javax.inject.Singleton

@Module(
    includes = [
        CommonDatabaseModule.Internal::class,
    ]
)
internal class CommonDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        appContext: Context,
    ): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME,
        ).build()


    @Module
    interface Internal {

        @Binds
        fun bindAnimeListDatabaseDelegate(
            appDatabase: AppDatabase,
        ): AnimeListDatabaseDelegate
    }
}