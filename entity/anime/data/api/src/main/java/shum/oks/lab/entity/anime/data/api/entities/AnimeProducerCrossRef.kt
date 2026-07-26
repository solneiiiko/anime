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
import androidx.room.Index

@Entity(
    tableName = AnimeProducerCrossRef.TABLE_NAME,
    primaryKeys = [
        AnimeProducerCrossRef.Column.ANIME_ID,
        AnimeProducerCrossRef.Column.PRODUCER_ID
    ],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = [AnimeEntity.Column.ID],
            childColumns = [AnimeProducerCrossRef.Column.ANIME_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnimeProducerEntity::class,
            parentColumns = [AnimeProducerEntity.Column.ID],
            childColumns = [AnimeProducerCrossRef.Column.PRODUCER_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            value = [
                AnimeProducerCrossRef.Column.PRODUCER_ID,
            ],
        ),
    ],
)
data class AnimeProducerCrossRef(
    @ColumnInfo(name = Column.ANIME_ID) val animeId: Int,
    @ColumnInfo(name = Column.PRODUCER_ID) val producerId: Int,
) {

    companion object {
        const val TABLE_NAME = "anime_producer_cross_ref"
    }

    object Column {
        const val ANIME_ID = "animeId"
        const val PRODUCER_ID = "producerId"
    }
}
