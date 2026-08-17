plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.compose)
}

android {
    namespace = "shum.oks.lab.feature.details.manga"
}

dependencies {
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}