/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.list.data.api

import shum.oks.lab.anime.list.data.api.dao.AnimeDao

interface AnimeListDatabaseDelegate {

    val animeDao: AnimeDao
}