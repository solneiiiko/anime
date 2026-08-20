/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey
import shum.oks.lab.feature.catalog.screens.models.CatalogElement

@Composable
internal fun CatalogListContent(
    pagingItems: LazyPagingItems<CatalogElement>,
    onItemClick: (CatalogItemKey) -> Unit,
    minCellSize: Dp,
    itemHeight: Dp,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minCellSize),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = WindowInsets.statusBars.asPaddingValues(),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.catalogItemKey },
            contentType = pagingItems.itemContentType { it.contentType }
        ) { index ->
            pagingItems[index]?.let { catalogElement ->
                CatalogItem(
                    anime = catalogElement,
                    itemHeight = itemHeight,
                    modifier = Modifier.clickable {
                        onItemClick(catalogElement.catalogItemKey)
                    }
                )
            }
        }

        when (val append = pagingItems.loadState.append) {
            is LoadState.Loading -> {
                // TODO https://github.com/solneiiiko/anime/issues/31 count of placeholders should be equal to page size
                repeat(3) {
                    item {
                        CatalogPlaceholderCard()
                    }
                }
            }
            is LoadState.Error -> {
                item {
                    AppendError(
                        errorText = append.error.toString(),
                        onRetry = { pagingItems.retry() },
                    )
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}

private val CatalogElement.contentType: String
    get() = "catalog_element"
