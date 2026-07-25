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
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity

@Database(
    entities = [
        AnimeEntity::class,
        AnimePaginationEntity::class,
        AnimeProducerEntity::class,
        AnimeProducerCrossRef::class,
    ],
    version = AppDatabase.VERSION,
)
internal abstract class AppDatabase : RoomDatabase(),
    AnimeDatabaseDelegate
{

    companion object {
        private const val VERSION = 1
        const val NAME = "shum_oks_lab_anime_database"
    }
}
