/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention.extensions

import com.android.build.api.dsl.Lint
import java.io.File

internal fun Lint.configureLint(
    checkDependencies: Boolean = false,
    lintConfig: File,
) {
    apply {
        abortOnError = true
        warningsAsErrors = true
        checkGeneratedSources = false
        checkReleaseBuilds=true
        checkTestSources = false
        this.checkDependencies = checkDependencies
        this.lintConfig = lintConfig
    }
}
