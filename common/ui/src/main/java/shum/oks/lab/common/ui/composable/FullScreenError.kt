/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shum.oks.lab.common.ui.R
import shum.oks.lab.common.ui.preview.AnimeThemePreview
import shum.oks.lab.core.ui.models.UiText
import shum.oks.lab.core.ui.preview.ThemePreviews

@Composable
fun FullScreenError(
    buttonInfo: ButtonInfo,
    modifier: Modifier = Modifier,
    title: UiText = UiText.StringResource(R.string.common_ui_error_something_went_wrong),
    subtitle: UiText? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.common_ui_error_image),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = title.asString(),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error,
            maxLines = TitleMaxLines,
            overflow = TextOverflow.Ellipsis,
        )
        subtitle?.let {
            Text(
                text = it.asString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = SubtitleMaxLines,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Button(
            onClick = buttonInfo.onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(all = 12.dp),
            modifier = Modifier
                .height(50.dp)
                .widthIn(max = 250.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = buttonInfo.text.asString(),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

data class ButtonInfo(
    val text: UiText,
    val onClick: () -> Unit,
)

private const val TitleMaxLines = 1
private const val SubtitleMaxLines = 3

@ThemePreviews
@Composable
private fun FullScreenErrorPreview() {
    AnimeThemePreview {
        FullScreenError(
            subtitle = UiText.Plain("Something went wrong"),
            buttonInfo = ButtonInfo(
                text = UiText.Plain("Retry"),
                onClick = {
                    // Nothing to do. All right. ^_^__/
                }
            ),
        )
    }
}
