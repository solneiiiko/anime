plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "shum.oks.lab.feature.details.anime"
}

dependencies {
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.kotlinx.collections.immutable)

    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)

    implementation(libs.androidx.foundation)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":core:di"))
    ksp(libs.dagger.compiler)
    implementation(project(":core:mvi"))
    api(project(":entity:anime:domain:api"))
    implementation(project(":core:ui"))
    implementation(project(":common:ui"))

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
