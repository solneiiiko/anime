/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.formatters

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Locale

internal class NumberFormatterTest {

    private val originalLocale = Locale.getDefault()
    private val formatter = NumberFormatter()

    @BeforeEach
    fun before() {
        Locale.setDefault(Locale.US)
    }

    @AfterEach
    fun after() {
        Locale.setDefault(originalLocale)
    }

    @Test
    fun `formatScore formats double to two decimal places`() {
        assertEquals("8.50", formatter.formatScore(8.5))
        assertEquals("8.57", formatter.formatScore(8.567))
        assertEquals("9.00", formatter.formatScore(9.0))
        assertEquals("0.00", formatter.formatScore(0.0))
    }

    @Test
    fun `formatCommon formats values less than 10,000 with grouping separator`() {
        assertEquals("500", formatter.formatCommon(500))
        assertEquals("9,999", formatter.formatCommon(9999))
        assertEquals("1,234", formatter.formatCommon(1234))
    }

    @Test
    fun `formatCommon formats values between 10,000 and 1,000,000 with K suffix`() {
        assertEquals("1.0K", formatter.formatCommon(10000))
        assertEquals("50.0K", formatter.formatCommon(500000)) // 500,000 / 10,000 = 50.0
        assertEquals("99.9K", formatter.formatCommon(999000)) // 999,000 / 10,000 = 99.9
    }

    @Test
    fun `formatCommon formats values 1,000,000 and above with M suffix`() {
        assertEquals("1.0M", formatter.formatCommon(1000000))
        assertEquals("1.5M", formatter.formatCommon(1500000))
        assertEquals("10.0M", formatter.formatCommon(10000000))
    }

    @Test
    fun `formatCommon rounds correctly at K to M boundary`() {
        assertEquals("100.0K", formatter.formatCommon(999999))
    }

    @Test
    fun `formatCommon formats negative values without grouping shortening`() {
        assertEquals("-500", formatter.formatCommon(-500))
        assertEquals("-9,999", formatter.formatCommon(-9999))
        assertEquals("-1,234", formatter.formatCommon(-1234))
    }

    @Test
    fun `formatCommon applies K or M suffix to large negative values preserving sign`() {
        assertEquals("-1.5K", formatter.formatCommon(-15000))
        assertEquals("-100.0K", formatter.formatCommon(-999999))
        assertEquals("-1.5M", formatter.formatCommon(-1500000))
        assertEquals("-10.0M", formatter.formatCommon(-10000000))
    }

    @Test
    fun `formatCommon handles Int MIN_VALUE without crashing`() {
        assertEquals("-2,147.5M", formatter.formatCommon(Int.MIN_VALUE))
    }

    @Test
    fun `formatScore formats negative scores with two decimal places`() {
        assertEquals("-1.00", formatter.formatScore(-1.0))
        assertEquals("-8.57", formatter.formatScore(-8.567))
        assertEquals("-0.50", formatter.formatScore(-0.5))
        assertEquals("-9.00", formatter.formatScore(-9.0))
    }

    @Test
    fun `formatScore handles negative zero`() {
        assertEquals("-0.00", formatter.formatScore(-0.0))
    }
}
