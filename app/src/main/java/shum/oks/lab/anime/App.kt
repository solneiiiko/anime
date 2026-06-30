/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime

import android.app.Application
import android.content.Context
import shum.oks.lab.anime.di.AppDependencies
import shum.oks.lab.anime.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appDependencies(
                object : AppDependencies {
                    override val appContext: Context = this@App
                }
            )
            .build()
            .componentHolderInitializer.init()
    }
}