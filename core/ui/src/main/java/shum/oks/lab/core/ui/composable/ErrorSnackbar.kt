/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.preview.ThemePreviews

@Composable
fun ErrorSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    action: Action? = null,
    icon: ImageVector? = Icons.Default.Info,
    maxLines: Int = MaxLines,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.padding(end = 8.dp),
            )
        }
        Text(
            text = message,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onErrorContainer,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
        )
        action?.takeIf { it.label.isNotBlank() }?.let { action ->
            TextButton(
                onClick = { action.onAction() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = action.label,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

private const val MaxLines = 3

data class Action(
    val label: String,
    val onAction: () -> Unit,
)

@ThemePreviews
@Composable
private fun ErrorSnackbarWithActionPreview() {
    ErrorSnackbar(
        message = "Something went wrong ".repeat(3),
        action = Action(label = "Retry", onAction = {}),
    )
}

@ThemePreviews
@Composable
private fun ErrorSnackbarWithoutActionPreview() {
    ErrorSnackbar(
        icon = null,
        message = "Something went wrong",
    )
}
