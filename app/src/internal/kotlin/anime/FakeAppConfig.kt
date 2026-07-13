/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime

import shum.oks.lab.entity.config.domain.api.AppConfigRepository
import shum.oks.lab.entity.config.domain.api.model.AppConfig
import shum.oks.lab.entity.config.domain.api.model.CacheConfig
import shum.oks.lab.entity.config.domain.api.model.PagingConfig
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class FakeAppConfigRepository @Inject constructor() : AppConfigRepository {

    override suspend fun getAppConfig(): AppConfig =
        AppConfig(
            pagingConfig = PagingConfig(
                pageSize = 25
            ),
            cacheConfig = CacheConfig(
                ttl = 1.toDuration(DurationUnit.MINUTES)
            )
        )
}
