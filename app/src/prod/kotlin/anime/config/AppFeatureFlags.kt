/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.config

/**
 * FeatureFlags intentionally ignore the server configuration
 */
internal object AppFeatureFlags : FeatureFlags {

    override val favouritesEnabled: Boolean = false
}
