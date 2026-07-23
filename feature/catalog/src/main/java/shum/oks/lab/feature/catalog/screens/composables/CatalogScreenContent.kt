/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey
import shum.oks.lab.feature.catalog.screens.mvi.CatalogUiIntent
import shum.oks.lab.feature.catalog.screens.mvi.CatalogUiViewModel

@Composable
internal fun CatalogScreenContent(
    viewModel: CatalogUiViewModel,
    modifier: Modifier = Modifier,
) {
    val animePagingData = viewModel.animePagingData.collectAsLazyPagingItems()
    val onItemClick = remember(viewModel) {
        { catalogItemKey: CatalogItemKey ->
            viewModel.handleIntent(CatalogUiIntent.ItemClicked(catalogItemKey))
        }
    }

    when (val refreshState = animePagingData.loadState.refresh) {
        is LoadState.Loading -> {
            CatalogSkeleton(
                minCellSize = MIN_CELL_SIZE,
                itemHeight = ITEM_HEIGHT,
                modifier = modifier,
            )
        }
        is LoadState.Error -> {
            FullScreenError(
                text = refreshState.error.toString(),
                onRetry = animePagingData::retry,
            )
        }
        is LoadState.NotLoading -> {
            CatalogListContent(
                pagingItems = animePagingData,
                minCellSize = MIN_CELL_SIZE,
                itemHeight = ITEM_HEIGHT,
                modifier = modifier,
                onItemClick = onItemClick,
            )
        }
    }
}

private val MIN_CELL_SIZE = 150.dp
private val ITEM_HEIGHT = 300.dp
