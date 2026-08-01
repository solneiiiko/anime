/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.composable.toolbar.CollapsingToolbar
import shum.oks.lab.core.ui.composable.toolbar.Title
import shum.oks.lab.core.ui.composable.toolbar.TitlePlacement
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnimeDetailsContent(
    animeDetailsUi: AnimeDetailsUi,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO UI is not ready https://github.com/solneiiiko/anime/issues/17
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Column(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(
                scrollBehavior.nestedScrollConnection,
            ),
    ) {
        Box {
            HeaderBackground(
                title = animeDetailsUi.headerInfo.title,
                imageUrl = animeDetailsUi.headerInfo.imageUrl,
                modifier = Modifier.matchParentSize()
            )
            CollapsingToolbar(
                collapsedTitle = Title(
                    text = animeDetailsUi.headerInfo.title,
                    textStyle = MaterialTheme.typography.headlineMedium,
                    placement = TitlePlacement.CenterVertically(
                        end = 16.dp,
                    )
                ),
                expandedTitle = Title(
                    text = animeDetailsUi.headerInfo.title,
                    textStyle = MaterialTheme.typography.headlineLarge,
                    placement = TitlePlacement.Padded(
                        paddingValues = PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        )
                    )
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClicked() },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                containerColor = Color.Transparent,
                expandedContent = {
                    ExpandedAnimeHeader(
                        headerInfoUi = animeDetailsUi.headerInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 24.dp,
                            ),
                    )
                },
                scrollBehavior = scrollBehavior,
                modifier = modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            repeat(10) { // TODO for scroll https://github.com/solneiiiko/anime/issues/17
                item {
                    Text(
                        text = animeDetailsUi.toString(),
                    )
                }
            }
        }
    }
}
