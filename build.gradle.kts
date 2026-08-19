/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.androidx.room) apply false
}

dependencyAnalysis {
    issues {
        all {
            onAny {
                severity("fail")
            }
            onUsedTransitiveDependencies {
                severity("ignore")
            }
            onIncorrectConfiguration {
                exclude(
                    (libs.dagger).moduleId,
                    (libs.androidx.compose.material3).moduleId,
                    (libs.kotlinx.collections.immutable).moduleId,
                )
            }
        }

        /**
         * TODO Need check after update buildHealth
         * The Dependency Analysis plugin is only known to work with versions of AGP between 8.10.0 and 9.2.1. You are using 9.3.0. Proceed at your own risk.
         */
        project(":app") {
            onIncorrectConfiguration {
                exclude(":core:di")
            }
        }
        project(":common:database") {
            onIncorrectConfiguration {
                exclude(libs.androidx.room.runtime)
            }
        }
        project(":common:network") {
            onIncorrectConfiguration {
                exclude(
                    libs.okhttp,
                    libs.logging.interceptor,
                    libs.kotlinx.serialization.json,
                )
            }
        }
        project(":core:mvi") {
            onUnusedDependencies {
                exclude(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }
        project(":core:ui") {
            onUnusedDependencies {
                exclude(libs.mockk.android)
            }
        }
        project(":entity:anime:data:api") {
            onUnusedDependencies {
                exclude(
                    libs.androidx.paging.common,
                    libs.androidx.room.paging,
                    libs.androidx.room.runtime,
                )
            }
        }
        project(":entity:anime:data:impl") {
            onUnusedDependencies {
                exclude(
                    libs.androidx.datastore.preferences,
                    libs.kotlinx.serialization.json,
                )
            }
        }
        project(":entity:anime:domain:api") {
            onUnusedDependencies {
                exclude(
                    libs.androidx.paging.common,
                )
            }
        }
        project(":feature:details:anime") {
            onIncorrectConfiguration {
                exclude(":core:ui")
            }
        }
    }
}

private val ProviderConvertible<MinimalExternalModuleDependency>.moduleId: String
    get() = asProvider().get().module.toString()

private val Provider<MinimalExternalModuleDependency>.moduleId: String
    get() = get().module.toString()
