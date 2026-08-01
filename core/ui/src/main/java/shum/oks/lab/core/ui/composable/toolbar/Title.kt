/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable.toolbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Title(
    val text: String,
    val textStyle: TextStyle,
    val placement: TitlePlacement,
)

@Immutable
sealed interface TitlePlacement {

    data class CenterVertically(
        val start: Dp = 0.dp,
        val end: Dp = 0.dp,
    ) : TitlePlacement

    data class Padded(
        val paddingValues: PaddingValues
    ) : TitlePlacement
}
