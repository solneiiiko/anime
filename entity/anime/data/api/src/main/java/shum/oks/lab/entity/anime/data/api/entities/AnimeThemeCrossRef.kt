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
    tableName = AnimeThemeCrossRef.TABLE_NAME,
    primaryKeys = [
        AnimeThemeCrossRef.Column.ANIME_ID,
        AnimeThemeCrossRef.Column.THEME_ID
    ],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = [AnimeEntity.Column.ID],
            childColumns = [AnimeThemeCrossRef.Column.ANIME_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeThemeEntity::class,
            parentColumns = [AnimeThemeEntity.Column.ID],
            childColumns = [AnimeThemeCrossRef.Column.THEME_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class AnimeThemeCrossRef(
    @ColumnInfo(name = Column.ANIME_ID) val animeId: Int,
    @ColumnInfo(name = Column.THEME_ID) val themeId: Int,
) {

    companion object {
        const val TABLE_NAME = "anime_theme_cross_ref"
    }

    object Column {
        const val ANIME_ID = "animeId"
        const val THEME_ID = "themeId"
    }
}
