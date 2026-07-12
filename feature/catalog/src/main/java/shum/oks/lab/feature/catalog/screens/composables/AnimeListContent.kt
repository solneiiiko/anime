/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import shum.oks.lab.feature.catalog.screens.models.Anime

@Composable
internal fun AnimeListContent(
    modifier: Modifier,
    pagingItems: LazyPagingItems<Anime>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
            contentType = pagingItems.itemContentType { "ololo" } // TODO
        ) { index ->
            pagingItems[index]?.let { anime ->
                AnimeItem(anime = anime)
            }
        }

        when (val append = pagingItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    AppendLoading()
                }
            }
            is LoadState.Error -> {
                item {
                    AppendError(
                        errorText = append.error.toString(),
                        onRetry = { pagingItems.retry() }
                    )
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}
