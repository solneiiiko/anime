/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.network

data class NetworkConfig(
    val baseUrl: String,
    val loggingLevel: LoggingLevel,
) {

    enum class LoggingLevel {
        NONE,
        BODY,
    }
}