/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("SecretsReaderPlugin") {
            id = "shum.oks.lab.anime.secrets"
            implementationClass = "shum.oks.lab.anime.convention.SecretsReaderPlugin"
        }
        register("AndroidApplicationPlugin") {
            id = "shum.oks.lab.anime.application"
            implementationClass = "shum.oks.lab.anime.convention.AndroidApplicationPlugin"
        }
        register("AndroidLibraryPlugin") {
            id = "shum.oks.lab.anime.library"
            implementationClass = "shum.oks.lab.anime.convention.AndroidLibraryPlugin"
        }
        register("AndroidComposePlugin") {
            id = "shum.oks.lab.anime.compose"
            implementationClass = "shum.oks.lab.anime.convention.AndroidComposePlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}
