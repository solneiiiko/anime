/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.common.database.di

import shum.oks.lab.anime.list.data.api.AnimeListDatabaseDelegate
import shum.oks.lab.core.di.BaseApi

interface CommonDatabaseApi : BaseApi {

    val animeListDatabaseDelegate: AnimeListDatabaseDelegate
}