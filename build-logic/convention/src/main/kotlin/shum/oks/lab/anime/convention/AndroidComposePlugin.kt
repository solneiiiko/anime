/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import shum.oks.lab.anime.convention.extensions.libs

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = libs
            pluginManager.apply(libs.kotlinCompilerPluginId)

            extensions.configure<CommonExtension> {
                buildFeatures.compose = true
            }
            dependencies {
                val configurationName = "implementation"
                add(configurationName, platform( libs.composeBom))
                add(configurationName, libs.composeBundle)

                add("debugImplementation", libs.uiTooling)
            }
        }
    }
}

private val VersionCatalog.kotlinCompilerPluginId: String
    get() = findPlugin("kotlin-compose")
        .get()
        .get()
        .pluginId

private val VersionCatalog.composeBom
    get() = findLibrary("androidx-compose-bom").get()

private val VersionCatalog.composeBundle
    get() = findBundle("androidx-compose").get()

private val VersionCatalog.uiTooling
    get() = findLibrary("androidx-compose-ui-tooling").get()
