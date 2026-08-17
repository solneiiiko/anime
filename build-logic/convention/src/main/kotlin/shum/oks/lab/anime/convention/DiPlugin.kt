/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies
import shum.oks.lab.anime.convention.extensions.libs

class DiPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = libs
            pluginManager.apply(libs.kspPluginId)
            dependencies {
                add("implementation", project(":core:di"))
                add("ksp", libs.daggerCompiler)
            }
        }
    }
}

private val VersionCatalog.kspPluginId: String
    get() = findPlugin("ksp")
        .get()
        .get()
        .pluginId

private val VersionCatalog.daggerCompiler
    get() = findLibrary("dagger-compiler").get()
