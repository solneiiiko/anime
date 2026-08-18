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
import shum.oks.lab.anime.convention.extensions.api
import shum.oks.lab.anime.convention.extensions.implementation
import shum.oks.lab.anime.convention.extensions.ksp
import shum.oks.lab.anime.convention.extensions.libs

class DiPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = libs
            pluginManager.apply(libs.plugins.ksp.get().pluginId)
            dependencies {
                implementation(project(":core:di"))
                api(libs.dagger)
                ksp(libs.dagger.compiler)
            }
        }
    }
}
