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
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class KotlinLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            extensions.configure<KotlinJvmProjectExtension> {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }
}
