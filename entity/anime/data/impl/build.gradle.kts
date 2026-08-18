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
    alias(libs.plugins.kotlin.serialization)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(libs.retrofit)
    implementation(libs.kotlinx.serialization.retrofit)
    implementation(libs.kotlinx.serialization.json)

    api(libs.androidx.datastore.preferences)

    implementation(project(":core:network"))

    implementation(project(":entity:anime:data:api"))
    api(project(":entity:anime:domain:api"))
    api(project(":entity:config:domain:api"))
}
