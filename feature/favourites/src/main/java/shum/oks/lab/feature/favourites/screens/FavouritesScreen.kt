/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.favourites.screens

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun FavouritesScreen(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "I'm FAVOURITES",
        modifier = modifier.statusBarsPadding(),
    )
}
