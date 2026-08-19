plugins {
    id("java-library")
    alias(libs.plugins.anime.kotlin.library)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
}
