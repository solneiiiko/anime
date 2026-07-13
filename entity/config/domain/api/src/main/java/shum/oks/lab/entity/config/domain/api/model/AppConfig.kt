/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.config.domain.api.model

data class AppConfig(
    val pagingConfig: PagingConfig,
    val cacheConfig: CacheConfig,
)
