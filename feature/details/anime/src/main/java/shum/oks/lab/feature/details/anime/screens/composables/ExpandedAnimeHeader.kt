/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import shum.oks.lab.core.ui.composable.ErrorImagePlaceholder
import shum.oks.lab.core.ui.composable.LoadingImagePlaceholder
import shum.oks.lab.core.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.preview.ThemePreviews
import shum.oks.lab.entity.anime.domain.api.models.AnimeType
import shum.oks.lab.feature.details.anime.screens.models.HeaderInfoUi

// TODO work in progress https://github.com/solneiiiko/anime/issues/17
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
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 16.dp, bottom = 24.dp)
        ) {
            // TODO https://github.com/solneiiiko/anime/issues/17
            Text(
                text = "Type: ${headerInfoUi.type.name}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = "Episodes: ${headerInfoUi.episodes}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Members: ${headerInfoUi.members}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Score: ${headerInfoUi.score}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
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
                .blur(16.dp) // TODO <= Android 12
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
                score = 8.5,
                type = AnimeType.TV,
                episodes = 12,
                members = 1000,

            ),
            modifier = Modifier
        )
    }
}
