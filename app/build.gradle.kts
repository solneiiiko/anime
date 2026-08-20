import shum.oks.lab.anime.convention.getSecret

/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

plugins {
    alias(libs.plugins.anime.application)
    alias(libs.plugins.anime.secrets)
    alias(libs.plugins.anime.compose)
    alias(libs.plugins.anime.di)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "shum.oks.lab.anime"

    defaultConfig {
        applicationId = "shum.oks.lab.anime"
        versionCode = 1

        val myAnimeListClientId = getSecret("MY_ANIME_LIST_CLIENT_ID") ?: ""
        buildConfigField("String", "MY_ANIME_LIST_CLIENT_ID", "\"$myAnimeListClientId\"")
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    val flavourDimensionEnvironment = "environment"
    val productFlavorInternal = "internal"
    val productFlavorProduction = "prod"
    flavorDimensions += listOf(flavourDimensionEnvironment)
    productFlavors {
        create(productFlavorProduction) {
            dimension = flavourDimensionEnvironment
        }

        create(productFlavorInternal) {
            dimension = flavourDimensionEnvironment
            applicationIdSuffix = ".$productFlavorInternal"
            versionNameSuffix = "-$productFlavorInternal"
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.material)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.compose.material3.navigation.suite)

    implementation(project(":core:mvi"))
    implementation(project(":core:ui"))

    implementation(project(":common:theme"))
    implementation(project(":common:database"))
    implementation(project(":common:network"))

    implementation(project(":entity:anime:data:impl"))
    implementation(project(":entity:anime:domain:impl"))

    implementation(project(":feature:settings"))
    implementation(project(":feature:catalog"))
    implementation(project(":feature:favourites"))
    implementation(project(":feature:details:anime"))

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    debugRuntimeOnly(libs.androidx.compose.ui.test.manifest)
}
