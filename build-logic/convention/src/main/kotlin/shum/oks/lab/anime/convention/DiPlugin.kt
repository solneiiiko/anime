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
import org.gradle.kotlin.dsl.dependencies
import shum.oks.lab.anime.convention.extensions.libs

class DiPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = libs
            pluginManager.apply(libs.plugins.ksp.get().pluginId)
            dependencies {
                val configurationName = "implementation"
                add(configurationName, project(":core:di"))
                add("api", libs.dagger)
                add("ksp", libs.dagger.compiler)
            }
        }
    }
}
