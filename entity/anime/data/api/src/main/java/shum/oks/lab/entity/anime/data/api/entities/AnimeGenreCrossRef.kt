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
import androidx.room.ForeignKey

@Entity(
    tableName = AnimeGenreCrossRef.TABLE_NAME,
    primaryKeys = [
        AnimeGenreCrossRef.Column.ANIME_ID,
        AnimeGenreCrossRef.Column.GENRE_ID
    ],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = [AnimeEntity.Column.ID],
            childColumns = [AnimeGenreCrossRef.Column.ANIME_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeGenreEntity::class,
            parentColumns = [AnimeGenreEntity.Column.ID],
            childColumns = [AnimeGenreCrossRef.Column.GENRE_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class AnimeGenreCrossRef(
    @ColumnInfo(name = Column.ANIME_ID) val animeId: Int,
    @ColumnInfo(name = Column.GENRE_ID) val genreId: Int,
) {

    companion object {
        const val TABLE_NAME = "anime_genre_cross_ref"
    }

    object Column {
        const val ANIME_ID = "animeId"
        const val GENRE_ID = "genreId"
    }
}
