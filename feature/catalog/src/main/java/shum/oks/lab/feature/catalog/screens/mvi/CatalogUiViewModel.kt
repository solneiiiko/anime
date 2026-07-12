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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeListUseCase
import shum.oks.lab.core.mvi.BaseViewModel
import shum.oks.lab.feature.catalog.di.CatalogUiComponentHolder
import shum.oks.lab.feature.catalog.screens.mappers.toUiModel
import shum.oks.lab.feature.catalog.screens.models.Anime
import javax.inject.Inject
import javax.inject.Provider

internal class CatalogUiViewModel @Inject constructor(
    getAnimeListUseCase: GetAnimeListUseCase,
) : BaseViewModel<
        CatalogUiState,
        CatalogUiIntent,
        CatalogUiEffect,
>(
    initialState = CatalogUiState.Loading,
    initialIntent = CatalogUiIntent.Init,
    onClearedCallback = { CatalogUiComponentHolder.clean() }
) {

    val animePagingData: Flow<PagingData<Anime>> =
        getAnimeListUseCase()
            .map { it.map { anime -> anime.toUiModel() } }
            .cachedIn(viewModelScope)

    override suspend fun handleIntent(intent: CatalogUiIntent) {
        when (intent) {
            CatalogUiIntent.Init -> {
                // TODO
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
