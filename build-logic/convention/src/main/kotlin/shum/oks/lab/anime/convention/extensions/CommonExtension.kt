/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import shum.oks.lab.anime.convention.AndroidConfig

internal fun CommonExtension.configureAndroidCommon() {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig.apply {
        minSdk = AndroidConfig.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions.apply {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}
