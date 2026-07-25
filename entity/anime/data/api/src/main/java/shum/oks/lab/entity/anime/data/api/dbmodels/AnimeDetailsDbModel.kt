/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.dbmodels

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import shum.oks.lab.entity.anime.data.api.entities.AnimeEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity

data class AnimeDetailsDbModel(
    @Embedded
    val anime: AnimeEntity,

    @Relation (
        parentColumn = AnimeEntity.Column.ID,
        entityColumn = AnimeProducerEntity.Column.ID,
        associateBy = Junction(
            value = AnimeProducerCrossRef::class,
            parentColumn = AnimeProducerCrossRef.Column.ANIME_ID,
            entityColumn = AnimeProducerCrossRef.Column.PRODUCER_ID
        )
    )
    val producers: List<AnimeProducerEntity>,
    val licensors: String? = null,
    val genres: String? = null,
    val themes: String? = null,
    val studios: String? = null,
)
