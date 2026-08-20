/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.window.core.layout.WindowSizeClass
import shum.oks.lab.anime.mvi.models.TopNavButtonUi
import shum.oks.lab.core.ui.utils.findActivity
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
    val context = LocalContext.current

    val navigator = remember(navigationState) {
        Navigator(
            state = navigationState,
            onExit = {
                context.findActivity()?.finish()
            }
        )
    }
    // It's a crunch for Navigation 3 ... TODO https://github.com/solneiiiko/anime/issues/47
    BackHandler(
        enabled = navigationState.getBackAction() == BackAction.EXIT
    ) {
        navigator.goBack()
    }
    val catalogNavigator = remember(navigator) { createCatalogNavigator(navigator) }
    val animeDetailsNavigator = remember(navigator) { createAnimeDetailsNavigator(navigator) }

    val entryProvider = remember(catalogNavigator, animeDetailsNavigator) {
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
                            contentDescription = button.label.asString()
                        )
                    },
                    label = {
                        Text(text = button.label.asString())
                    }
                )
            }
        },
        modifier = modifier,
    ) {
        NavDisplay(
            onBack = {
                navigator.goBack()
            },
            entries = navigationState.toDecoratedEntries(entryProvider),
            transitionSpec = {
                if (navigationState.transitionType != NavigationTransitionType.SWITCH_TOP_LEVEL) {
                    createTransitionContentTransform()
                } else {
                    createSwitchTopLevelTransitionContentTransform()
                }
            },
            popTransitionSpec = {
                if (navigationState.transitionType != NavigationTransitionType.SWITCH_TOP_LEVEL) {
                    createPopTransitionContentTransform()
                } else {
                    createSwitchTopLevelTransitionContentTransform()
                }
            },
            predictivePopTransitionSpec = {
                when (navigationState.getBackAction()) {
                    BackAction.SWITCH_TOP_LEVEL -> createSwitchTopLevelTransitionContentTransform()
                    BackAction.POP, BackAction.EXIT -> createPopTransitionContentTransform()
                }
            }
        )
    }
}

private fun createPopTransitionContentTransform(): ContentTransform =
    slideInHorizontally(
        initialOffsetX = { -it / 4 },
        animationSpec = tween(TransitionDurationMillis),
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(TransitionDurationMillis),
    )

private fun createTransitionContentTransform(): ContentTransform =
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(TransitionDurationMillis),
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { -it / 4 },
        animationSpec = tween(TransitionDurationMillis),
    )

private fun createSwitchTopLevelTransitionContentTransform(): ContentTransform =
    ContentTransform(
        fadeIn(animationSpec = tween(TransitionDurationMillis)),
        fadeOut(animationSpec = tween(TransitionDurationMillis)),
    )

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

private const val TransitionDurationMillis = 200
