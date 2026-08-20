/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion

internal fun CommonExtension.configureAndroidCommon(
    versions: LibrariesForLibs.VersionAccessors,
) {
    compileSdk = versions.compileSdk.get().toInt()

    defaultConfig.apply {
        minSdk = versions.minSdk.get().toInt()
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
