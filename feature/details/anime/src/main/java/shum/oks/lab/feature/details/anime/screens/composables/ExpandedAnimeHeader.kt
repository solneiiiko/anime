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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        HeaderBackground(
            title = headerInfoUi.title,
            imageUrl = headerInfoUi.imageUrl,
            modifier = Modifier.matchParentSize()
        )
        HeaderContent(
            headerInfoUi = headerInfoUi,
            onBackClicked = onBackClicked,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
fun HeaderBackground(
    title: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .blur(16.dp) // TODO <= Android 12
                .graphicsLayer {
                    scaleX = 5f
                    scaleY = 5f
                }
                .matchParentSize()
                .clipToBounds()
            ,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeaderContent(
    headerInfoUi: HeaderInfoUi,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text(
                    text = headerInfoUi.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "null",
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            ),
        )
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.Bottom,
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(headerInfoUi.imageUrl)
                    .build(),
                contentDescription = headerInfoUi.title,
                modifier = Modifier
                    .width(140.dp)
                    .padding(start = 24.dp, bottom = 24.dp)
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
                // TOODO https://github.com/solneiiiko/anime/issues/17
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
            onBackClicked = { /* Nothing to do. All right. ^_^__/ */ },
            modifier = Modifier
        )
    }
}
