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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import shum.oks.lab.feature.catalog.di.CatalogUiComponentHolder
import shum.oks.lab.feature.catalog.screens.composables.AnimeListContent
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
            CatalogSkeleton(modifier = modifier)
        }
        is LoadState.Error -> {
            FullScreenError(
                text = refreshState.error.toString(),
                onRetry = animePagingData::retry,
            )
        }
        is LoadState.NotLoading -> {
            AnimeListContent(
                modifier = modifier,
                pagingItems = animePagingData
            )
        }
    }
}
