/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

plugins {
    id("java-library")
    alias(libs.plugins.anime.kotlin.library)
    alias(libs.plugins.anime.di)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(project(":entity:anime:domain:api"))
}
