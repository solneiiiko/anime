plugins {
    id("java-library")
    alias(libs.plugins.anime.kotlin.library)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    api(libs.retrofit)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)

    testImplementation(libs.jqwik.api)
    testRuntimeOnly(libs.jqwik.kotlin)
    testRuntimeOnly(libs.jqwik.engine)

    testImplementation(libs.mockwebserver)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.retrofit.converter.scalars)
}
