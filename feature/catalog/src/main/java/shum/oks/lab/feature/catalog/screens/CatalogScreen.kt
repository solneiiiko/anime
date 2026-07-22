/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import shum.oks.lab.feature.catalog.di.CatalogUiComponentHolder
import shum.oks.lab.feature.catalog.screens.composables.CatalogListContent
import shum.oks.lab.feature.catalog.screens.composables.CatalogSkeleton
import shum.oks.lab.feature.catalog.screens.composables.FullScreenError
import shum.oks.lab.feature.catalog.screens.mvi.CatalogUiViewModel

@Composable
internal fun CatalogScreen(
    viewModel: CatalogUiViewModel = viewModel(
        factory = CatalogUiComponentHolder.get().viewModelFactory
    ),
    modifier: Modifier = Modifier,
) {
    val animePagingData = viewModel.animePagingData.collectAsLazyPagingItems()

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
            )
        }
    }
}

private val MIN_CELL_SIZE = 150.dp
private val ITEM_HEIGHT = 300.dp