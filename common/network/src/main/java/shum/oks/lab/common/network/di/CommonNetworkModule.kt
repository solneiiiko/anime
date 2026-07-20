/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.network.di

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import shum.oks.lab.common.network.NetworkConfig
import shum.oks.lab.common.network.interceptors.MyAnimeListHeaderInterceptor
import shum.oks.lab.common.network.qualifiers.JikanNetwork
import shum.oks.lab.common.network.qualifiers.MyAnimeListNetwork
import javax.inject.Singleton

@Module
internal object CommonNetworkModule {

    @Singleton
    @JikanNetwork
    @Provides
    fun provideJikanRetrofit(
        @JikanNetwork okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(JIKAN_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.Json))
            .build()

    @Singleton
    @MyAnimeListNetwork
    @Provides
    fun provideMyAnimeListRetrofit(
        @MyAnimeListNetwork okHttpClient: OkHttpClient,
        json: Json,
    ) : Retrofit =
        Retrofit.Builder()
            .baseUrl(MY_ANIME_LIST_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.Json))
            .build()

    @JikanNetwork
    @Provides
    fun provideJikanClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @MyAnimeListNetwork
    @Provides
    fun provideMyAnimeListClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: MyAnimeListHeaderInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor(
        networkConfig: NetworkConfig,
    ): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = when (networkConfig.loggingLevel) {
                NetworkConfig.LoggingLevel.NONE -> HttpLoggingInterceptor.Level.NONE
                NetworkConfig.LoggingLevel.BODY -> HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    fun provideJson(): Json =
        Json { ignoreUnknownKeys = true }

    private const val JIKAN_BASE_URL = "https://api.jikan.moe/"
    private const val MY_ANIME_LIST_BASE_URL = "https://api.myanimelist.net/v2/"
}

private val MediaType.Companion.Json: MediaType
    get() = "application/json".toMediaType()
