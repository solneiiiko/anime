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
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    api(libs.androidx.room.paging)
    api(libs.androidx.paging.common)
}
