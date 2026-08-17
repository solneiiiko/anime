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
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import kotlinx.collections.immutable.toImmutableList
import shum.oks.lab.common.ui.composable.ErrorImagePlaceholder
import shum.oks.lab.common.ui.composable.LoadingImagePlaceholder
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.common.ui.preview.AnimeThemePreview
import shum.oks.lab.common.ui.preview.ThemePreviews
import shum.oks.lab.feature.details.anime.R
import shum.oks.lab.feature.details.anime.screens.models.HeaderBlockUi
import shum.oks.lab.feature.details.anime.screens.models.HeaderInfoUi

@Composable
internal fun ExpandedAnimeHeader(
    headerInfoUi: HeaderInfoUi,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(headerInfoUi.imageUrl)
                .build(),
            contentDescription = headerInfoUi.title,
            modifier = Modifier
                .width(150.dp)
                .aspectRatio(0.7f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            error = {
                ErrorImagePlaceholder()
            },
            loading = {
                LoadingImagePlaceholder()
            }
        )
        FlowRow(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 2,
        ) {
            headerInfoUi.headerBlockUis.forEach { headerBlock ->
                Surface(
                    modifier = Modifier.weight(0.5f),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(all = 4.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = headerBlock.subtitle.asString(),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = headerBlock.title.asString(),
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun HeaderBackground(
    title: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds(),
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .blur(16.dp) // TODO <= Android 12 https://github.com/solneiiiko/anime/issues/17
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopStart,
            error = {
                ErrorImagePlaceholder()
            },
            loading = {
                LoadingImagePlaceholder()
            }
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.35f))
        )
    }
}

@ThemePreviews
@Composable
private fun ExpandedAnimeHeaderPreview() {
    AnimeThemePreview {
        ExpandedAnimeHeader(
            headerInfoUi = HeaderInfoUi(
                title = "Anime details very very very very very very very very very very very very",
                imageUrl = null,
                headerBlockUis = listOf(
                    HeaderBlockUi(
                        title = UiText.StringResource(R.string.anime_details_score_title),
                        subtitle = UiText.Plain("8.50")
                    ),
                    HeaderBlockUi(
                        title = UiText.StringResource(R.string.anime_details_rank_title),
                        subtitle = UiText.Plain("#1")
                    ),
                    HeaderBlockUi(
                        title = UiText.StringResource(R.string.anime_details_members_title),
                        subtitle = UiText.Plain("1,000")
                    ),
                    HeaderBlockUi(
                        title = UiText.StringResource(R.string.anime_details_favorites_title),
                        subtitle = UiText.Plain("500")
                    )
                ).toImmutableList(),
            ),
            modifier = Modifier
        )
    }
}
