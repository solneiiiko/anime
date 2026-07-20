/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.repositories

import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog

data class PaginationInfo(
    val currentPage: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val pageSize: Int,
    val catalog: AnimeCatalog,
)
