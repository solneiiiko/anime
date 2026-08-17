/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import shum.oks.lab.common.ui.preview.AnimeThemePreview
import shum.oks.lab.common.ui.preview.ThemePreviews
import shum.oks.lab.core.ui.composable.toolbar.CollapsingToolbar
import shum.oks.lab.core.ui.composable.toolbar.Title
import shum.oks.lab.core.ui.composable.toolbar.TitlePlacement
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUiPreviewData
import shum.oks.lab.core.ui.R as CoreUiR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnimeDetailsContent(
    animeDetailsUi: AnimeDetailsUi,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Column(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(
                scrollBehavior.nestedScrollConnection,
            )
            .background(MaterialTheme.colorScheme.background),
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
                        onClick = onBackClicked,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(CoreUiR.string.core_ui_accessibility_back),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 24.dp,
            ),
        ) {
            item {
                MetadataRow(
                    metadata = animeDetailsUi.metadata,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                )
            }
            animeDetailsUi.synopsisInfo?.let { synopsisInfo ->
                item {
                    ExpandableTextCard(expandableTextInfoUi = synopsisInfo)
                }
            }
            // TODO Trailer Preview https://github.com/solneiiiko/anime/issues/17
            animeDetailsUi.genresInfo?.let { genresInfo ->
                item {
                    TagsCard(
                        tagsInfoUi = genresInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            animeDetailsUi.themesInfo?.let { themesInfo ->
                item {
                    TagsCard(
                        tagsInfoUi = themesInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            animeDetailsUi.statisticsInfo?.let { statisticsInfo ->
                item {
                    LabeledValuesCard(
                        labeledValuesInfoUi = statisticsInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            animeDetailsUi.productionInfo?.let { productionInfo ->
                item {
                    LabeledValuesCard(
                        labeledValuesInfoUi = productionInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            animeDetailsUi.producersInfo?.let { producersInfo ->
                item {
                    TagsCard(
                        tagsInfoUi = producersInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            animeDetailsUi.backgroundInfo?.let { backgroundInfo ->
                item {
                    ExpandableTextCard(expandableTextInfoUi = backgroundInfo)
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun AnimeDetailsContentPreview() {
    AnimeThemePreview {
        AnimeDetailsContent(
            animeDetailsUi = AnimeDetailsUiPreviewData.animeDetailsUi(),
            onBackClicked = {
                // Nothing to do. All right. ^_^__/
            }
        )
    }
}

