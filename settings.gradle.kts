/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "anime"
include(":app")

include(":core:di")
include(":core:network")
include(":core:mvi")
include(":core:ui")

include(":common:database")
include(":common:network")
include(":common:theme")

include(":entity:anime:data:api")
include(":entity:anime:data:impl")
include(":entity:anime:domain:api")
include(":entity:anime:domain:impl")
include(":entity:config:domain:api")
include(":entity:settings")

include(":feature:catalog")
include(":feature:settings")
include(":feature:favourites")
include(":feature:details:anime")
include(":feature:details:manga")
