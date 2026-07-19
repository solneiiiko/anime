/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.catalog.screens.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import shum.oks.lab.core.mvi.BaseViewModel
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeListUseCase
import shum.oks.lab.feature.catalog.di.CatalogUiComponentHolder
import shum.oks.lab.feature.catalog.screens.mappers.CatalogNumberFormatter
import shum.oks.lab.feature.catalog.screens.mappers.toUiModel
import shum.oks.lab.feature.catalog.screens.models.CatalogElement
import javax.inject.Inject
import javax.inject.Provider

internal class CatalogUiViewModel @Inject constructor(
    getAnimeListUseCase: GetAnimeListUseCase,
    numberFormatter: CatalogNumberFormatter,
) : BaseViewModel<
        CatalogUiState,
        CatalogUiIntent,
        CatalogUiEffect,
>(
    initialState = CatalogUiState.Loading,
    initialIntent = CatalogUiIntent.Init,
    onClearedCallback = { CatalogUiComponentHolder.clean() }
) {

    val animePagingData: Flow<PagingData<CatalogElement>> =
        flow {
            emitAll(getAnimeListUseCase())
        }
        .map { pagingData -> pagingData.map { it.toUiModel(numberFormatter) } }
        .cachedIn(viewModelScope)

    override suspend fun handleIntent(intent: CatalogUiIntent) {
        when (intent) {
            CatalogUiIntent.Init -> {
                // TODO to get tabs to show Manga & Anime https://github.com/solneiiiko/anime/issues/31
            }
        }
    }

    class Factory @Inject constructor(
        private val provider: Provider<CatalogUiViewModel>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            provider.get() as T
    }
}
