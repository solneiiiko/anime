plugins {
    alias(libs.plugins.anime.library)
    alias(libs.plugins.anime.di)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "shum.oks.lab.common.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}
/**
 * TODO Remove when Room Gradle Plugin correctly propagates room.schemaLocation to KSP for this toolchain.
 * https://issuetracker.google.com/issues/379159770  ????
 */
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    api(project(":entity:anime:data:api"))

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}
