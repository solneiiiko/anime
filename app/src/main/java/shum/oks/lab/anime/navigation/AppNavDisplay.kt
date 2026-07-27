/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.window.core.layout.WindowSizeClass
import shum.oks.lab.anime.mvi.models.TopNavButtonUi
import shum.oks.lab.feature.catalog.navigation.CatalogScreenKey
import shum.oks.lab.feature.catalog.navigation.catalogEntryProviders
import shum.oks.lab.feature.details.anime.navigation.animeDetailsProviders
import shum.oks.lab.feature.favourites.navigation.favouritesEntryProviders
import shum.oks.lab.feature.settings.navigation.settingsEntryProviders

@Composable
internal fun AppNavDisplay(
    navButtons: List<TopNavButtonUi>,
    modifier: Modifier = Modifier,
) {
    val navigationState = rememberNavigationState(
        startRoute = CatalogScreenKey,
        topLevelRoutes = navButtons.map { it.navKey }.toSet()
    )
    val navigator = remember { Navigator(navigationState) }
    val catalogNavigator = remember { createCatalogNavigator(navigator) }
    val animeDetailsNavigator = remember { createAnimeDetailsNavigator(navigator) }

    val entryProvider = remember {
        entryProvider {
            settingsEntryProviders()
            catalogEntryProviders(
                navigator = catalogNavigator,
            )
            favouritesEntryProviders()
            animeDetailsProviders(
                navigator = animeDetailsNavigator
            )
        }
    }
    NavigationSuiteScaffold(
        layoutType = getNavigationSuiteTypeByWindowSize(),
        navigationSuiteItems = {
            navButtons.forEach { button ->
                val isSelected = button.navKey == navigationState.topLevelRoute
                item(
                    selected = isSelected,
                    onClick = {
                        navigator.navigateTo(button.navKey)
                    },
                    icon = {
                        Icon(
                            imageVector = button.icon,
                            contentDescription = stringResource(button.label)
                        )
                    },
                    label = {
                        Text(text = stringResource(button.label))
                    }
                )
            }
        }
    ) {
        NavDisplay(
            onBack = {
                navigator.goBack()
            },
            entries = navigationState.toDecoratedEntries(entryProvider),
            modifier = modifier,
        )
    }
}

@Composable
private fun getNavigationSuiteTypeByWindowSize(): NavigationSuiteType {
    val windowSize = with(LocalDensity.current) {
        currentWindowSize().toSize().toDpSize()
    }
    return if (windowSize.width >= WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND.dp)
        NavigationSuiteType.NavigationRail
    else
        NavigationSuiteType.NavigationBar
}
