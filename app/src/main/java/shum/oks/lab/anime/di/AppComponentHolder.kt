/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di

import android.content.Context
import shum.oks.lab.core.di.BaseComponentHolder

internal object AppComponentHolder : BaseComponentHolder<
    AppComponentInternalApi,
    AppDependencies,
>() {

    override fun buildComponent(dependencies: AppDependencies): AppComponentInternalApi =
        DaggerAppComponent.builder()
            .appDependencies(
                object : AppDependencies {
                    override val appContext: Context = dependencies.appContext
                }
            )
            .build()

    override fun afterInit() {
        super.afterInit()
        get().componentHolderInitializer.init()
    }
}
