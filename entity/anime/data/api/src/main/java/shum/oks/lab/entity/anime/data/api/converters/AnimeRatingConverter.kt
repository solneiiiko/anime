/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.converters

import androidx.room.TypeConverter
import shum.oks.lab.entity.anime.data.api.entities.AnimeRating

class AnimeRatingConverter { // TODO

    @TypeConverter
    fun fromRating(rating: AnimeRating?): String? =
        rating?.value

    @TypeConverter
    fun toRating(value: String?): AnimeRating? =
        value?.let { v -> AnimeRating.entries.find { it.value == v } }
}
