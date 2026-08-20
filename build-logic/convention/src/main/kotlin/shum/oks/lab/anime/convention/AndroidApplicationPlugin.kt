/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import shum.oks.lab.anime.convention.extensions.configureAndroidCommon
import shum.oks.lab.anime.convention.extensions.configureLint
import shum.oks.lab.anime.convention.extensions.libs
import shum.oks.lab.anime.convention.extensions.lintConfig

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            apply<DetektPlugin>()

            extensions.configure<ApplicationExtension> {
                configureAndroidCommon(
                    versions = libs.versions,
                )
                defaultConfig.apply {
                    targetSdk = libs.versions.targetSdk.get().toInt()
                    versionName = libs.versions.appVersionName.get()
                }
                lint.configureLint(
                    checkDependencies = true,
                    lintConfig = target.lintConfig,
                )

            }
        }
    }
}
