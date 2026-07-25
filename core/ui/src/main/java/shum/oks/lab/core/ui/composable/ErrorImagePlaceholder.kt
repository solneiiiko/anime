/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import shum.oks.lab.core.ui.R

@Composable
fun ErrorImagePlaceholder(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(R.drawable.core_ui_image_placeholder),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
