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
import javax.inject.Singleton

@Module
internal object CommonNetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        networkConfig: NetworkConfig,
        json: Json,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(networkConfig.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.Json))
            .build()

    @Provides
    fun provideClient(
        loggInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggInterceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor(
        netConfig: NetworkConfig,
    ): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = when (netConfig.loggingLevel) {
                NetworkConfig.LoggingLevel.NONE -> HttpLoggingInterceptor.Level.NONE
                NetworkConfig.LoggingLevel.BODY -> HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    fun provideJson(): Json =
        Json { ignoreUnknownKeys = true }
}

private val MediaType.Companion.Json: MediaType
    get() = "application/json".toMediaType()
