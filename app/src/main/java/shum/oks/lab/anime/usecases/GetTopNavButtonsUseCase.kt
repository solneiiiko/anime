/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.usecases

import shum.oks.lab.anime.config.FeatureFlags
import shum.oks.lab.anime.usecases.models.TopNavButton
import javax.inject.Inject

internal class GetTopNavButtonsUseCase @Inject constructor(
    private val featureFlags: FeatureFlags,
) {

    operator fun invoke(): List<TopNavButton> = buildList {
        add(TopNavButton.CATALOG)
        if (featureFlags.favouritesEnabled) {
            add(TopNavButton.FAVOURITES)
        }
        add(TopNavButton.MORE)
    }
}
