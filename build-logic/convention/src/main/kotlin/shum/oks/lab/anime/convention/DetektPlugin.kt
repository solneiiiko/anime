/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention

import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import shum.oks.lab.anime.convention.extensions.libs

class DetektPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugins.detekt.get().pluginId)

            extensions.configure<DetektExtension> {
                toolVersion.set(libs.versions.detekt.get())

                config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
                buildUponDefaultConfig.set(true)
            }

            tasks.withType<Detekt>().configureEach {
                reports {
                    html.required.set(true)
                    sarif.required.set(false)
                    checkstyle.required.set(false)
                    markdown.required.set(false)
                }
            }
        }
    }
}
