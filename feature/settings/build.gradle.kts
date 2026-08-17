plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "shum.oks.lab.feature.settings"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)

    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}