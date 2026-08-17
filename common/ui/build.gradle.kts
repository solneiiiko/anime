plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "shum.oks.lab.common.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.coil.compose)

    api(project(":core:ui"))
    implementation(project(":common:theme"))
}
