/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.converters

import androidx.room.TypeConverter
import shum.oks.lab.entity.anime.data.api.entities.AnimeType

class AnimeTypeConverter {

    @TypeConverter
    fun fromType(type: AnimeType?): String? =
        type?.value

    @TypeConverter
    fun toType(value: String?): AnimeType? =
        value?.let { v -> AnimeType.entries.find { it.value == v } }
}
