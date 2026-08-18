/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "shum.oks.lab.feature.favourites"
}

dependencies {
    api(libs.androidx.navigation3.runtime)

    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}
