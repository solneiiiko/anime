/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import shum.oks.lab.common.database.di.CommonDatabaseComponentHolder
import shum.oks.lab.common.network.di.CommonNetworkComponentHolder
import shum.oks.lab.core.di.DependenciesProvider
import shum.oks.lab.entity.anime.data.api.AnimeDatabaseDelegate
import shum.oks.lab.entity.anime.data.impl.di.EntityAnimeDataImplComponentHolder
import shum.oks.lab.entity.anime.data.impl.di.EntityAnimeDataImplDependencies
import shum.oks.lab.entity.anime.domain.api.repositories.AnimeRepository
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeListUseCase
import shum.oks.lab.entity.anime.domain.impl.di.EntityAnimeDomainImplComponentHolder
import shum.oks.lab.entity.anime.domain.impl.di.EntityAnimeDomainImplDependencies
import shum.oks.lab.feature.catalog.di.CatalogUiDependencies

@Module
class CatalogModule {

    @Provides
    fun provideCatalogUiDependenciesProvider(
    ): DependenciesProvider<CatalogUiDependencies> = {
        object : CatalogUiDependencies {
            override val getAnimeListUseCase: GetAnimeListUseCase
                get() = EntityAnimeDomainImplComponentHolder.get().getAnimeListUseCase
        }
    }

    @Provides
    fun provideAnimeListDomainImplDependenciesProvider(
    ): DependenciesProvider<EntityAnimeDomainImplDependencies> = {
        object : EntityAnimeDomainImplDependencies {
            override val animeRepository: AnimeRepository
                get() = EntityAnimeDataImplComponentHolder.get().animeRepository
        }
    }

    @Provides
    fun provideAnimeListDataImplDependenciesProvider(
    ): DependenciesProvider<EntityAnimeDataImplDependencies> = {
        object : EntityAnimeDataImplDependencies {
            override val retrofit: Retrofit
                get() = CommonNetworkComponentHolder.get().retrofit

            override val animeDatabaseDelegate: AnimeDatabaseDelegate
                get() = CommonDatabaseComponentHolder.get().animeDatabaseDelegate
        }
    }
}
