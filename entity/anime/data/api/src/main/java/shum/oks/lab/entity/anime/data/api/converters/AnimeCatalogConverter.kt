/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.api.converters

import androidx.room.TypeConverter
import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog

class AnimeCatalogConverter {

    @TypeConverter
    fun fromCatalog(catalog: AnimeCatalog?): String? =
        catalog?.key

    @TypeConverter
    fun toCatalog(value: String?): AnimeCatalog? =
        value?.let { v -> AnimeCatalog.entries.find { it.key == v } }
}
