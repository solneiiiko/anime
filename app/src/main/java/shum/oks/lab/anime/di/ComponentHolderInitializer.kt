/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di

import shum.oks.lab.common.database.di.CommonDatabaseComponentHolder
import shum.oks.lab.common.database.di.CommonDatabaseDependencies
import shum.oks.lab.common.network.di.CommonNetworkComponentHolder
import shum.oks.lab.common.network.di.CommonNetworkDependencies
import shum.oks.lab.core.di.DependenciesProvider
import shum.oks.lab.entity.anime.data.impl.di.EntityAnimeDataImplComponentHolder
import shum.oks.lab.entity.anime.data.impl.di.EntityAnimeDataImplDependencies
import shum.oks.lab.entity.anime.domain.impl.di.EntityAnimeDomainImplComponentHolder
import shum.oks.lab.entity.anime.domain.impl.di.EntityAnimeDomainImplDependencies
import shum.oks.lab.feature.catalog.di.CatalogUiComponentHolder
import shum.oks.lab.feature.catalog.di.CatalogUiDependencies
import shum.oks.lab.feature.details.anime.di.AnimeDetailsUiComponentHolder
import shum.oks.lab.feature.details.anime.di.AnimeDetailsUiDependencies
import javax.inject.Inject

class ComponentHolderInitializer @Inject constructor(
    private val commonDatabaseDependenciesProvider: DependenciesProvider<CommonDatabaseDependencies>,
    private val commonNetworkDependenciesProvider: DependenciesProvider<CommonNetworkDependencies>,
    private val entityAnimeDataImplDependenciesProvider: DependenciesProvider<EntityAnimeDataImplDependencies>,
    private val entityAnimeDomainImplDependenciesProvider: DependenciesProvider<EntityAnimeDomainImplDependencies>,
    private val catalogUiDependenciesProvider: DependenciesProvider<CatalogUiDependencies>,
    private val animeDetailsUiDependenciesProvider: DependenciesProvider<AnimeDetailsUiDependencies>,
) {

    fun init() {
        initCommon()
        initFeatures()
    }

    private fun initCommon() {
        CommonDatabaseComponentHolder.init(commonDatabaseDependenciesProvider)
        CommonNetworkComponentHolder.init(commonNetworkDependenciesProvider)
    }

    private fun initFeatures() {
        EntityAnimeDataImplComponentHolder.init(entityAnimeDataImplDependenciesProvider)
        EntityAnimeDomainImplComponentHolder.init(entityAnimeDomainImplDependenciesProvider)
        CatalogUiComponentHolder.init(catalogUiDependenciesProvider)
        AnimeDetailsUiComponentHolder.init(animeDetailsUiDependenciesProvider)
    }
}