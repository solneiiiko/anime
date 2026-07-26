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
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeGenreEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeLicensorEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeProducerEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeStudioEntity
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeCrossRef
import shum.oks.lab.entity.anime.data.api.entities.AnimeThemeEntity

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

    @Relation(
        parentColumn = AnimeEntity.Column.ID,
        entityColumn = AnimeLicensorEntity.Column.ID,
        associateBy = Junction(
            value = AnimeLicensorCrossRef::class,
            parentColumn = AnimeLicensorCrossRef.Column.ANIME_ID,
            entityColumn = AnimeLicensorCrossRef.Column.LICENSOR_ID
        )
    )
    val licensors: List<AnimeLicensorEntity>,

    @Relation(
        parentColumn = AnimeEntity.Column.ID,
        entityColumn = AnimeStudioEntity.Column.ID,
        associateBy = Junction(
            value = AnimeStudioCrossRef::class,
            parentColumn = AnimeStudioCrossRef.Column.ANIME_ID,
            entityColumn = AnimeStudioCrossRef.Column.STUDIO_ID
        )
    )
    val studios: List<AnimeStudioEntity>,

    @Relation(
        parentColumn = AnimeEntity.Column.ID,
        entityColumn = AnimeGenreEntity.Column.ID,
        associateBy = Junction(
            value = AnimeGenreCrossRef::class,
            parentColumn = AnimeGenreCrossRef.Column.ANIME_ID,
            entityColumn = AnimeGenreCrossRef.Column.GENRE_ID
        )
    )
    val genres: List<AnimeGenreEntity>,

    @Relation(
        parentColumn = AnimeEntity.Column.ID,
        entityColumn = AnimeThemeEntity.Column.ID,
        associateBy = Junction(
            value = AnimeThemeCrossRef::class,
            parentColumn = AnimeThemeCrossRef.Column.ANIME_ID,
            entityColumn = AnimeThemeCrossRef.Column.THEME_ID
        )
    )
    val themes: List<AnimeThemeEntity>,
)
