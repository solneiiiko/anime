/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CollapsingToolbarLayoutUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val collapsedTitle = Title(
        text = "Collapsed Title",
        textStyle = TextStyle(fontSize = 18.sp),
        placement = TitlePlacement.CenterVertically(start = 16.dp)
    )

    private val expandedTitle = Title(
        text = "Expanded Title",
        textStyle = TextStyle(fontSize = 30.sp),
        placement = TitlePlacement.Padded(PaddingValues(16.dp))
    )

    @Test
    fun expandedState_displaysExpandedTitle() {
        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = collapsedTitle,
                collapsedProgress = 0f,
                heightOffset = 0f,
                onHeightOffsetLimitChange = {}
            )
        }
        composeTestRule.onNodeWithTag(ExpandedTitleTestTag).assertIsDisplayed()
    }

    @Test
    fun collapsedState_displaysCollapsedTitle() {
        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = collapsedTitle,
                collapsedProgress = 1f,
                heightOffset = 0f,
                onHeightOffsetLimitChange = {}
            )
        }
        composeTestRule.onNodeWithTag(CollapsedTitleTestTag).assertIsDisplayed()
    }

    @Test
    fun onHeightOffsetLimitChange_isCalled() {
        val callback = mockk<(Float) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = collapsedTitle,
                collapsedProgress = 0f,
                heightOffset = 0f,
                onHeightOffsetLimitChange = callback
            )
        }
        verify(exactly = 1) { callback(any()) }
    }

    @Test
    fun navigationIcon_isDisplayedWhenProvided() {
        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = collapsedTitle,
                collapsedProgress = 1f,
                heightOffset = 0f,
                navigationIcon = {
                    Box(Modifier.size(48.dp))
                },
                onHeightOffsetLimitChange = {}
            )
        }
        composeTestRule.onNodeWithTag(NavigationIconTestTag).assertIsDisplayed()
    }

    @Test
    fun expandedContent_isDisplayedWhenProvided() {
        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = collapsedTitle,
                collapsedProgress = 0f,
                heightOffset = 0f,
                expandedContent = {
                    Box(Modifier.size(100.dp))
                },
                onHeightOffsetLimitChange = {}
            )
        }
        composeTestRule.onNodeWithTag(ExpandedContentTestTag).assertIsDisplayed()
    }

    @Test
    fun navigationIconNull_collapsedTitleStartsAtLeftEdge() {
        val zeroStartCollapsedTitle = collapsedTitle.copy(
            placement = TitlePlacement.CenterVertically()
        )

        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = zeroStartCollapsedTitle,
                collapsedProgress = 1f,
                heightOffset = 0f,
                navigationIcon = null,
                onHeightOffsetLimitChange = {}
            )
        }
        composeTestRule.onNodeWithTag(CollapsedTitleTestTag)
            .assertLeftPositionInRootIsEqualTo(0.dp)
    }

    @Test
    fun longCollapsedTitle_isConstrainedByMaxWidth_insteadOfOverflowing() {
        val maxWidth = 80.dp
        val longTitle = Title(
            text = "A very long collapsed title that would never fit on one line",
            textStyle = TextStyle(fontSize = 18.sp),
            placement = TitlePlacement.CenterVertically()
        )

        composeTestRule.setContent {
            CollapsingToolbarLayout(
                expandedTitle = expandedTitle,
                collapsedTitle = longTitle,
                collapsedProgress = 1f,
                heightOffset = 0f,
                modifier = Modifier.width(maxWidth),
                onHeightOffsetLimitChange = {}
            )
        }
        val maxWidthPx = with(composeTestRule.density) { maxWidth.roundToPx() }
        val node = composeTestRule.onNodeWithTag(CollapsedTitleTestTag).fetchSemanticsNode()
        assertTrue(
            "Collapsed title width (${node.size.width}px) should not exceed maxWidth ($maxWidthPx px)",
            node.size.width <= maxWidthPx
        )
    }
}
