/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.ui.formatters

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.abs

class NumberFormatter {

    fun formatScore(score: Double): String =
        FormatType.SCORE.formatter.format(score)

    fun formatCommon(value: Int): String {
        val absValue = abs(value.toLong())
        return if (absValue >= MILLION_THRESHOLD) {
            FormatType.COMPACT_DECIMAL.formatter
                .format(value / MILLION_THRESHOLD.toDouble()) + MILLION_SUFFIX
        } else if (absValue >= TEN_THOUSAND_THRESHOLD) {
            FormatType.COMPACT_DECIMAL.formatter
                .format(value / THOUSAND_DIVISOR) + THOUSAND_SUFFIX
        } else {
            FormatType.COMMON_INT.formatter.format(value)
        }
    }

    private enum class FormatType(
        private val pattern: String,
    ) {
        SCORE("0.00"),
        COMPACT_DECIMAL("#,##0.0"),
        COMMON_INT("#,###"),
        ;

        val formatter: DecimalFormat by lazy {
            DecimalFormat(pattern, DecimalFormatSymbols(Locale.US))
        }
    }
}

private const val MILLION_THRESHOLD = 1_000_000
private const val TEN_THOUSAND_THRESHOLD = 10_000
private const val THOUSAND_DIVISOR = 1_000.0
private const val MILLION_SUFFIX = "M"
private const val THOUSAND_SUFFIX = "K"
