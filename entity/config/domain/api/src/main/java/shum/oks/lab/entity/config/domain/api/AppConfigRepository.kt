/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.config.domain.api

import shum.oks.lab.entity.config.domain.api.model.AppConfig

/**
 * Ideally, this repository returns the configuration used
 * for the current app session and fetches the latest version
 * from the server for the next app launch.
 */
interface AppConfigRepository {

    suspend fun getAppConfig(): AppConfig
}
