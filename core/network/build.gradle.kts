import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
        freeCompilerArgs.add("-java-parameters")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.retrofit)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)

    testImplementation(libs.jqwik.api)
    testImplementation(libs.jqwik.kotlin)
    testRuntimeOnly(libs.jqwik.engine)

    testImplementation(libs.mockwebserver)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.retrofit.converter.scalars)
}
