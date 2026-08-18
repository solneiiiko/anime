plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.compose)
}

android {
    namespace = "shum.oks.lab.common.ui"
}

dependencies {
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.tooling.preview)

    api(project(":core:ui"))
    implementation(project(":common:theme"))
}
