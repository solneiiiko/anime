plugins {
    alias(libs.plugins.anime.feature.ui)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "shum.oks.lab.feature.details.anime"
}

dependencies {
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.kotlinx.collections.immutable)

    api(libs.androidx.navigation3.runtime)

    implementation(libs.androidx.foundation)

    implementation(project(":core:mvi"))
    api(project(":entity:anime:domain:api"))
    implementation(project(":core:ui"))
    implementation(project(":common:ui"))

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}
