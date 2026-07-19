/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import shum.oks.lab.feature.catalog.screens.models.CatalogElement

@Composable
internal fun CatalogListContent(
    modifier: Modifier,
    pagingItems: LazyPagingItems<CatalogElement>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
            contentType = pagingItems.itemContentType { it.contentType }
        ) { index ->
            pagingItems[index]?.let { catalogElement ->
                CatalogItem(anime = catalogElement)
            }
        }

        when (val append = pagingItems.loadState.append) {
            is LoadState.Loading -> {
                repeat(3) { // TODO https://github.com/solneiiiko/anime/issues/31 count of placeholders should be equal to page size
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
