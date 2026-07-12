/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import shum.oks.lab.entity.anime.data.api.AnimeDatabaseDelegate
import shum.oks.lab.entity.anime.data.api.dao.AnimeDao
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity

@Database(
    entities = [
        AnimeEntity::class,
    ],
    version = AppDatabase.VERSION,
)
internal abstract class AppDatabase : RoomDatabase(),
    AnimeDatabaseDelegate
{

    abstract override val animeDao: AnimeDao

    companion object {
        private const val VERSION = 1
        const val NAME = "application_anime_database"
    }
}
