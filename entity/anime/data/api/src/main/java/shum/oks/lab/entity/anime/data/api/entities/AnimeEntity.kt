/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import shum.oks.lab.entity.anime.data.api.converters.AnimeRatingConverter
import shum.oks.lab.entity.anime.data.api.converters.AnimeTypeConverter

@Entity(
    tableName = AnimeEntity.TABLE_NAME,
)
@TypeConverters(
    AnimeTypeConverter::class,
    AnimeRatingConverter::class,
)
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = Column.ID) val id: Int,
    @ColumnInfo(name = Column.TITLE) val title: String,
    @ColumnInfo(name = Column.SMALL_IMAGE_URL) val smallImageUrl: String? = null,
    @ColumnInfo(name = Column.LARGE_IMAGE_URL) val largeImageUrl: String? = null,
    @ColumnInfo(name = Column.TRAILER_EMBED_URL) val trailerEmbedUrl: String? = null,
    @ColumnInfo(name = Column.SOURCE) val source: String? = null,
    @ColumnInfo(name = Column.DURATION) val duration: String? = null,
    @ColumnInfo(name = Column.RATING) val rating: AnimeRating? = null,
    @ColumnInfo(name = Column.SCORE) val score: Double? = null,
    @ColumnInfo(name = Column.SCORED_BY) val scoredBy: Int? = null,
    @ColumnInfo(name = Column.RANK) val rank: Int? = null,
    @ColumnInfo(name = Column.POPULARITY) val popularity: Int? = null,
    @ColumnInfo(name = Column.FAVORITES) val favorites: Int? = null,
    @ColumnInfo(name = Column.SYNOPSIS) val synopsis: String? = null,
    @ColumnInfo(name = Column.BACKGROUND) val background: String? = null,
    @ColumnInfo(name = Column.YEAR) val year: Int? = null,
    @ColumnInfo(name = Column.TYPE) val type: AnimeType? = null,
    @ColumnInfo(name = Column.EPISODES) val episodes: Int? = null,
    @ColumnInfo(name = Column.MEMBERS) val members: Int? = null,
) {

    companion object {
        const val TABLE_NAME = "anime"
    }

    object Column {
        const val ID = "id"
        const val TITLE = "title"
        const val SMALL_IMAGE_URL = "small_image_url"
        const val LARGE_IMAGE_URL = "large_image_url"
        const val TRAILER_EMBED_URL = "trailer_embed_url"
        const val SOURCE = "source"
        const val DURATION = "duration"
        const val RATING = "rating"
        const val SCORE = "score"
        const val SCORED_BY = "scored_by"
        const val RANK = "rank"
        const val POPULARITY = "popularity"
        const val FAVORITES = "favorites"
        const val SYNOPSIS = "synopsis"
        const val BACKGROUND = "background"
        const val YEAR = "year"
        const val TYPE = "type"
        const val EPISODES = "episodes"
        const val MEMBERS = "members"
    }
}
