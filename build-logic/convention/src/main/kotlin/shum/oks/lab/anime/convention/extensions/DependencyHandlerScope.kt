/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.convention.extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandlerScope.api(dependency: Any) {
    add("api", dependency)
}

internal fun DependencyHandlerScope.ksp(dependency: Any) {
    add("ksp", dependency)
}

internal fun DependencyHandlerScope.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}