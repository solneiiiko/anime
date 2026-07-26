/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.dbmodels

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeType
import shum.oks.lab.entity.anime.data.api.converters.AnimeTypeConverter

data class AnimeSummaryDbModel(
    @ColumnInfo(name = AnimeEntity.Column.ID) val id: Int,
    @ColumnInfo(name = AnimeEntity.Column.TITLE) val title: String,
    @ColumnInfo(name = AnimeEntity.Column.SMALL_IMAGE_URL) val smallImageUrl: String? = null,
    @ColumnInfo(name = AnimeEntity.Column.SCORE) val score: Double?,
    @TypeConverters(AnimeTypeConverter::class)
    @ColumnInfo(name = AnimeEntity.Column.TYPE) val type: AnimeType? = null,
    @ColumnInfo(name = AnimeEntity.Column.EPISODES) val episodes: Int? = null,
    @ColumnInfo(name = AnimeEntity.Column.MEMBERS) val members: Int? = null,
)
