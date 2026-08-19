/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

plugins {
    alias(libs.plugins.anime.library)
}

android {
    namespace = "shum.oks.lab.core.mvi"
}

dependencies {
    api(libs.androidx.lifecycle.viewmodel.ktx)
}