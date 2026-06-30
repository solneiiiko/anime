/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.di

import javax.inject.Provider

abstract class BaseComponentHolder<
    BASE_API: BaseApi,
    BASE_DEPENDENCIES: BaseDependencies,
> {

    private lateinit var dependenciesProvider: Provider<BASE_DEPENDENCIES>
    private var component: BASE_API? = null

    fun init(dependenciesProvider: Provider<BASE_DEPENDENCIES>) {
        if (this::dependenciesProvider.isInitialized) {
            throw IllegalArgumentException("ComponentHolder is already initialized... >_<")
        }
        this.dependenciesProvider = dependenciesProvider
    }

    fun get(): BASE_API {
        if (!this::dependenciesProvider.isInitialized) {
            throw IllegalArgumentException("ComponentHolder is not initialized... >_<")
        }

        return component ?: synchronized(this) {
            component ?: buildComponent(dependenciesProvider.get()).also {
                component = it
            }
        }
    }

    protected abstract fun buildComponent(dependencies: BASE_DEPENDENCIES): BASE_API

    fun clean() {
        // TODO something before clean?? + check for UI!
        component = null
    }
}