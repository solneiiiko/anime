/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

// TODO wrap safety getter and setter with try-catch and handle exceptions (:core:datastore)
internal class AnimePreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun getLastRefreshTime(): Long? =
        dataStore.data.first()[Keys.LAST_REFRESH_TIME]

    suspend fun setLastRefreshTime(timeMillis: Long) {
        dataStore.edit { prefs ->
            prefs[Keys.LAST_REFRESH_TIME] = timeMillis
        }
    }

    suspend fun getCurrentPageSize(): Int? =
        dataStore.data.first()[Keys.CURRENT_PAGE_SIZE]

    suspend fun setCurrentPageSize(pageSize: Int) {
        dataStore.edit { prefs ->
            prefs[Keys.CURRENT_PAGE_SIZE] = pageSize
        }
    }

    private object Keys {
        val LAST_REFRESH_TIME = longPreferencesKey("shum.oks.lab.entity.anime.data.last_refresh_time")
        val CURRENT_PAGE_SIZE = intPreferencesKey("shum.oks.lab.entity.anime.data.current_page_size")
    }
}
