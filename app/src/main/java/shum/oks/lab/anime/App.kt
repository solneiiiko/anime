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
import shum.oks.lab.anime.di.AppComponentHolder
import shum.oks.lab.anime.di.AppDependencies

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponentHolder.init(dependenciesProvider = {
            object : AppDependencies {
                override val appContext: Context = this@App
            }
        })
    }
}
