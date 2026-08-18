plugins {
    id("java-library")
    alias(libs.plugins.anime.kotlin.library)
    alias(libs.plugins.ksp)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(libs.dagger)
    implementation(libs.androidx.annotation.jvm)
    ksp(libs.dagger.compiler)
}
