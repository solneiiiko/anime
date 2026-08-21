plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.compose)
}

android {
    namespace = "shum.oks.lab.core.ui"
}

dependencies {
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.animation)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.ui.tooling.preview)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.mockk)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)
    debugRuntimeOnly(libs.androidx.compose.ui.test.manifest)
}
