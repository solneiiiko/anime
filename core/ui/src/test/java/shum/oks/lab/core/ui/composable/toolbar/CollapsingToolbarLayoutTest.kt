/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.composable.toolbar

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CollapsingToolbarLayoutTest {

    @Test
    fun `expandedContentAlpha returns 1 when progress is 0`() {
        assertEquals(1f, expandedContentAlpha(0f))
    }

    @Test
    fun `expandedContentAlpha returns 0f when progress is ExpandedContentFadeEnd`() {
        assertEquals(0f, expandedContentAlpha(ExpandedContentFadeEnd))
    }

    @Test
    fun `expandedContentAlpha returns 0 when progress is 1`() {
        assertEquals(0f, expandedContentAlpha(1f))
    }

    @Test
    fun `expandedContentAlpha clamps values less than 0 to 1`() {
        assertEquals(1f, expandedContentAlpha(-1f))
    }

    @Test
    fun `expandedContentAlpha clamps values greater than 1 to 0`() {
        assertEquals(0f, expandedContentAlpha(2f))
    }

    @Test
    fun `TitlePaddingPx horizontalPaddingPx is sum of left and right`() {
        val padding = TitlePaddingPx(left = 10, right = 20, top = 5, bottom = 15)
        assertEquals(30, padding.horizontalPaddingPx)
    }

    @Test
    fun `TitlePaddingPx verticalPaddingPx is sum of top and bottom`() {
        val padding = TitlePaddingPx(left = 10, right = 20, top = 5, bottom = 15)
        assertEquals(20, padding.verticalPaddingPx)
    }
}
