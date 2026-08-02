/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.mvi.mappers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import shum.oks.lab.anime.R
import shum.oks.lab.anime.mvi.models.TopNavButtonUi
import shum.oks.lab.anime.usecases.models.TopNavButton
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.feature.catalog.navigation.CatalogScreenKey
import shum.oks.lab.feature.favourites.navigation.FavouritesScreenKey
import shum.oks.lab.feature.settings.navigation.SettingsScreenKey

internal fun List<TopNavButton>.toTopNavButtonUiList(): ImmutableList<TopNavButtonUi> =
    map {
        TopNavButtonUi(
            label = it.label,
            icon = it.icon,
            navKey = it.navKey,
        )
    }.toImmutableList()

private val TopNavButton.navKey
    get() = when (this) {
        TopNavButton.CATALOG -> CatalogScreenKey
        TopNavButton.FAVOURITES -> FavouritesScreenKey
        TopNavButton.MORE -> SettingsScreenKey
    }

private val TopNavButton.icon
    get() = when (this) {
        TopNavButton.CATALOG -> Icons.Outlined.Home
        TopNavButton.FAVOURITES -> Icons.Outlined.Favorite
        TopNavButton.MORE -> Icons.Outlined.Menu
    }

private val TopNavButton.label: UiText
    get() = when (this) {
        TopNavButton.CATALOG -> UiText.StringResource(R.string.shum_oks_lab_app_top_nav_button_catalog)
        TopNavButton.FAVOURITES -> UiText.StringResource(R.string.shum_oks_lab_app_top_nav_button_favourites)
        TopNavButton.MORE -> UiText.StringResource(R.string.shum_oks_lab_app_top_nav_button_more)
    }
