/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import retrofit2.Retrofit
import shum.oks.lab.entity.anime.data.api.AnimeDatabaseDelegate
import shum.oks.lab.core.di.BaseDependencies
import shum.oks.lab.entity.anime.data.impl.qualifiers.JikanNetwork
import shum.oks.lab.entity.config.domain.api.AppConfigRepository

interface EntityAnimeDataImplDependencies : BaseDependencies {

    @get:JikanNetwork
    val jikanRetrofit: Retrofit

    val animeDatabaseDelegate: AnimeDatabaseDelegate

    val appConfigRepository: AppConfigRepository

    val preferencesDataStore: DataStore<Preferences>
}
