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
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimePaginationEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeEntity

@Database(
    entities = [
        AnimeEntity::class,
        AnimePaginationEntity::class,
        AnimeProducerEntity::class,
        AnimeProducerCrossRef::class,
        AnimeLicensorEntity::class,
        AnimeLicensorCrossRef::class,
        AnimeStudioEntity::class,
        AnimeStudioCrossRef::class,
        AnimeGenreEntity::class,
        AnimeGenreCrossRef::class,
        AnimeThemeEntity::class,
        AnimeThemeCrossRef::class,
    ],
    version = AppDatabase.VERSION,
    exportSchema = true,
)
internal abstract class AppDatabase : RoomDatabase(),
    AnimeDatabaseDelegate
{

    companion object {
        private const val VERSION = 1
        const val NAME = "shum_oks_lab_anime_database"
    }
}
