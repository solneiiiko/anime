/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import shum.oks.lab.feature.catalog.navigation.CatalogNavigator
import shum.oks.lab.feature.catalog.screens.composables.CatalogScreenContent
import shum.oks.lab.feature.catalog.screens.mvi.CatalogUiEffect
import shum.oks.lab.feature.catalog.screens.mvi.CatalogUiViewModel

@Composable
internal fun CatalogScreen(
    viewModel: CatalogUiViewModel,
    navigator: CatalogNavigator,
) {
    LaunchedEffect(viewModel, navigator) {
        viewModel.effect.collect { effect ->
            handleLaunchEffect(navigator, effect)
        }
    }

    CatalogScreenContent(
        viewModel = viewModel,
        modifier = Modifier,
    )
}

private fun handleLaunchEffect(
    navigator: CatalogNavigator,
    effect: CatalogUiEffect,
) {
    when (effect) {
        is CatalogUiEffect.NavigateToDetails -> {
            navigator.openDetails(effect.catalogItemKey)
        }
    }
}
