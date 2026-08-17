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
import org.gradle.kotlin.dsl.apply

class FeatureUiPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply<AndroidLibraryPlugin>()
            apply<AndroidComposePlugin>()
            apply<DiPlugin>()
        }
    }
}
