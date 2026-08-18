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
    implementation(project(":core:network"))
    api(libs.retrofit)
    implementation(libs.kotlinx.serialization.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}
