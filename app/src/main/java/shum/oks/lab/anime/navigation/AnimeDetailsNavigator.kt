/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.navigation

import shum.oks.lab.feature.details.anime.navigation.AnimeDetailsNavigator

internal fun createAnimeDetailsNavigator(
    navigator: Navigator,
): AnimeDetailsNavigator = object : AnimeDetailsNavigator {

    override fun back() {
        navigator.goBack()
    }
}
