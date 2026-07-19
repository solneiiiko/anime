/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import shum.oks.lab.entity.anime.data.api.AnimeDatabaseDelegate
import shum.oks.lab.entity.anime.data.api.dao.AnimeDao
import shum.oks.lab.entity.anime.data.impl.api.AnimeApi
import shum.oks.lab.entity.anime.data.impl.qualifiers.JikanNetwork
import shum.oks.lab.entity.anime.data.impl.repositories.AnimeRemoteRepositoryImpl
import shum.oks.lab.entity.anime.domain.api.repositories.AnimeRepository

@Module
internal interface EntityAnimeDataImplModule {

    @Binds
    fun bindAnimeRepository(
        animeRepositoryImpl: AnimeRemoteRepositoryImpl
    ): AnimeRepository

    companion object {

        @JikanNetwork
        @Provides
        fun provideAnimeApi(
            @JikanNetwork retrofit: Retrofit,
        ): AnimeApi = retrofit.create(AnimeApi::class.java)

        @Provides
        fun provideAnimeDao(
            animeDatabaseDelegate: AnimeDatabaseDelegate,
        ): AnimeDao = animeDatabaseDelegate.animeDao
    }
}
