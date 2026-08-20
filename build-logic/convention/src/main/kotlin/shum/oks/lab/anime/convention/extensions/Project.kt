/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention.extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import java.io.File

internal val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

internal val Project.lintConfig: File
    get() = rootProject.file("config/lint/lint.xml")
