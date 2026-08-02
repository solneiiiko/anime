/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shum.oks.lab.core.ui.R
import shum.oks.lab.core.ui.models.UiText

@Composable
fun ExpandableText(
    text: String,
    collapsedMaxLines: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    showMoreText: UiText = UiText.StringResource(R.string.core_ui_show_more),
    showLessText: UiText? = UiText.StringResource(R.string.core_ui_show_less),
) {
    var expanded by rememberSaveable(text, collapsedMaxLines) {
        mutableStateOf(false)
    }

    var hasOverflow by remember(text, collapsedMaxLines) {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = AnimationDurationMillis,
                    easing = FastOutSlowInEasing,
                )
            )
    ) {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines,
            style = textStyle,
            onTextLayout = { result ->
                if (!expanded) {
                    hasOverflow = result.hasVisualOverflow
                }
            },
            overflow = TextOverflow.Ellipsis
        )
        if (hasOverflow || expanded) {
            val actionText = (if (expanded) showLessText?.asString() else showMoreText.asString())
            actionText?.let {
                TextButton(
                    onClick = { expanded = !expanded },
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 4.dp,
                    ),
                ) {
                    Text(
                        text = actionText,
                    )

                    Spacer(Modifier.width(4.dp))

                    Icon(
                        imageVector = if (expanded) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        },
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

private const val AnimationDurationMillis = 300
