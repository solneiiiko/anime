/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention

import org.gradle.api.Project
import java.util.Properties

fun Project.getSecret(key: String): String? {
    providers.gradleProperty(key).orNull?.let { return it }

    val file = rootProject.file(LOCAL_PROPERTIES_FILE_NAME)
    if (!file.exists()) return null

    val props = Properties().apply {
        file.inputStream().use(::load)
    }
    return props.getProperty(key)
}

private const val LOCAL_PROPERTIES_FILE_NAME = "local.properties"
