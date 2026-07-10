/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di

import shum.oks.lab.anime.mvi.AppViewModel
import shum.oks.lab.core.di.BaseApi

internal interface AppComponentInternalApi : BaseApi {

    val componentHolderInitializer: ComponentHolderInitializer

    val appViewModelFactory: AppViewModel.Factory
}
