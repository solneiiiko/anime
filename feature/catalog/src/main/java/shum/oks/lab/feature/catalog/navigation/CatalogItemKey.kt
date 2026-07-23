/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface CatalogItemKey : Parcelable {

    @Parcelize
    data class AnimeKey(
        val animeId: Int,
    ) : CatalogItemKey
}
