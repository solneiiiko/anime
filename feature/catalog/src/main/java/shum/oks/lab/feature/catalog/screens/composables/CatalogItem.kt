/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import shum.oks.lab.core.ui.modifiers.shimmer
import shum.oks.lab.core.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.preview.ThemePreviews
import shum.oks.lab.entity.settings.theme.models.ThemeContrast
import shum.oks.lab.feature.catalog.R
import shum.oks.lab.feature.catalog.navigation.CatalogItemKey
import shum.oks.lab.feature.catalog.screens.inlinetextcontent.CatalogInlineContentType
import shum.oks.lab.feature.catalog.screens.inlinetextcontent.catalogInlineContent
import shum.oks.lab.feature.catalog.screens.models.CatalogElement

@Composable
internal fun CatalogItem(
    anime: CatalogElement,
    itemHeight: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight),
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.imageUrl)
                .build(),
            contentDescription = anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .weight(1f),
            contentScale = ContentScale.Crop,
            error = {
                ErrorImagePlaceholder()
            },
            loading = {
                LoadingImagePlaceholder()
            }
        )
        ProvideTextStyle(
            value = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            )
        ) {
            Text(
                text = anime.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = anime.subtitle,
                inlineContent = catalogInlineContent,
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun LoadingImagePlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.feature_catalog_screen_item_placeholder),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmer()
        )
    }
}

@Composable
private fun ErrorImagePlaceholder(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(R.drawable.feature_catalog_screen_item_placeholder),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@ThemePreviews
@Composable
private fun CatalogItemStandardContrastPreview() {
    CatalogItemPreviewByContrast(themeContrast = ThemeContrast.STANDARD)
}

@ThemePreviews
@Composable
private fun CatalogItemMediumContrastPreview() {
    CatalogItemPreviewByContrast(themeContrast = ThemeContrast.MEDIUM)
}

@ThemePreviews
@Composable
private fun CatalogItemHighContrastPreview() {
    CatalogItemPreviewByContrast(themeContrast = ThemeContrast.HIGH)
}

@Composable
private fun CatalogItemPreviewByContrast(
    themeContrast: ThemeContrast,
) {
    AnimeThemePreview(
        themeContrast = themeContrast
    ) {
        CatalogItem(
            anime = CatalogElement(
                catalogItemKey = CatalogItemKey.AnimeKey(animeId = 1),
                title = "Sample Anime Title",
                subtitle = buildAnnotatedString {
                    append("TV(1,002) ")
                    appendInlineContent(CatalogInlineContentType.STAR_RATE.id)
                    append("8.98")
                    appendInlineContent(CatalogInlineContentType.MEMBERS.id)
                    append("72.0K")
                },
                imageUrl = "https://example.com/sample.jpg"
            ),
            itemHeight = 300.dp,
        )
    }
}
