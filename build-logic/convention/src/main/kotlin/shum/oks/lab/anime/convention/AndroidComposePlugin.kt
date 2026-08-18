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
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import shum.oks.lab.anime.convention.extensions.debugImplementation
import shum.oks.lab.anime.convention.extensions.implementation
import shum.oks.lab.anime.convention.extensions.libs

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = libs
            pluginManager.apply(libs.plugins.kotlin.compose.get().pluginId)

            extensions.configure<CommonExtension> {
                buildFeatures.compose = true
            }
            dependencies {
                implementation(platform(libs.androidx.compose.bom))
                implementation(libs.bundles.androidx.compose)

                debugImplementation(libs.androidx.compose.ui.tooling)
            }
        }
    }
}
