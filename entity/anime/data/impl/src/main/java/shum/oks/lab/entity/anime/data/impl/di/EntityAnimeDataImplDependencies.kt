/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.entity.anime.data.impl.di

import retrofit2.Retrofit
import shum.oks.lab.entity.anime.data.api.AnimeDatabaseDelegate
import shum.oks.lab.core.di.BaseDependencies

interface EntityAnimeDataImplDependencies : BaseDependencies {

    val retrofit: Retrofit

    val animeDatabaseDelegate: AnimeDatabaseDelegate
}
