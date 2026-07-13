/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.mappers

import shum.oks.lab.entity.anime.data.api.entities.AnimeSummaryEntity
import shum.oks.lab.entity.anime.data.impl.models.AnimeResponse

internal fun List<AnimeResponse>.toEntityModelList(): List<AnimeSummaryEntity> =
    map {
        AnimeSummaryEntity(
            id = it.id,
            title = it.title,
        )
    }
