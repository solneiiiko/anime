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
import shum.oks.lab.anime.list.data.api.AnimeListDatabaseDelegate
import shum.oks.lab.anime.list.data.api.entities.AnimeEntity

@Database(
    entities = [
        AnimeEntity::class,
    ],
    version = AppDatabase.VERSION,
)
internal abstract class AppDatabase : RoomDatabase(),
    AnimeListDatabaseDelegate
{

    companion object {
        private const val VERSION = 1
        const val NAME = "anime_database"
    }
}