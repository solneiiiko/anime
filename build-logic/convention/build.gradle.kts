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
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}
