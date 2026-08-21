/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.config

import shum.oks.lab.anime.BuildConfig
import shum.oks.lab.entity.anime.data.api.entities.AnimeCatalog

internal object AppEnvironment : Environment{

    override val networkLoggingEnabled: Boolean = BuildConfig.DEBUG

    override val defaultAnimeCatalog: AnimeCatalog = AnimeCatalog.ALL
}
