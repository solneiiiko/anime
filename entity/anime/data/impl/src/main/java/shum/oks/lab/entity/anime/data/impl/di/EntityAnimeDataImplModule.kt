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
import shum.oks.lab.entity.anime.data.impl.api.JikanAnimeApi
import shum.oks.lab.entity.anime.data.impl.api.MyAnimeListAnimeApi
import shum.oks.lab.entity.anime.data.impl.qualifiers.JikanNetwork
import shum.oks.lab.entity.anime.data.impl.qualifiers.MyAnimeListNetwork
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
        fun provideJikanAnimeApi(
            @JikanNetwork retrofit: Retrofit,
        ): JikanAnimeApi = retrofit.create(JikanAnimeApi::class.java)

        @MyAnimeListNetwork
        @Provides
        fun provideMyAnimeListAnimeApi(
            @MyAnimeListNetwork retrofit: Retrofit,
        ): MyAnimeListAnimeApi = retrofit.create(MyAnimeListAnimeApi::class.java)

        @Provides
        fun provideAnimeDao(
            animeDatabaseDelegate: AnimeDatabaseDelegate,
        ): AnimeDao = animeDatabaseDelegate.animeDao
    }
}
