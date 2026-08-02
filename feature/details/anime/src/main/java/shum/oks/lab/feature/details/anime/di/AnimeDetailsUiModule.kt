/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.di

import dagger.Module
import dagger.Provides
import shum.oks.lab.core.ui.formatters.NumberFormatter

@Module
internal class AnimeDetailsUiModule {

    @Provides
    fun provideNumberFormatter(): NumberFormatter =
        NumberFormatter()
}
