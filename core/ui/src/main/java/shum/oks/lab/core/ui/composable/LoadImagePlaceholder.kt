/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import shum.oks.lab.core.ui.R
import shum.oks.lab.core.ui.modifiers.shimmer

@Composable
fun LoadingImagePlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.core_ui_image_placeholder),
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
