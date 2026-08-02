/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import shum.oks.lab.core.mvi.BaseViewModel
import shum.oks.lab.core.ui.formatters.NumberFormatter
import shum.oks.lab.entity.anime.domain.api.usecases.GetAnimeDetailsUseCase
import shum.oks.lab.feature.details.anime.di.AnimeDetailsUiComponentHolder
import shum.oks.lab.feature.details.anime.screens.mappers.toUiModel

internal class AnimeDetailsViewModel @AssistedInject constructor(
    getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    numberFormatter: NumberFormatter,
    @Assisted val animeId: Int,
) : BaseViewModel<
    AnimeDetailsUiState,
    AnimeDetailsUiIntent,
    AnimeDetailsUiEffect,
>(
    initialState = AnimeDetailsUiState.Loading,
    onClearedCallback = {  AnimeDetailsUiComponentHolder.clean() } // TODO https://github.com/solneiiiko/anime/issues/17
) {

    init {
        viewModelScope.launch {
            getAnimeDetailsUseCase(animeId)
                .collect { dataEvent ->
                    updateState {
                        AnimeDetailsUiState.Content(
                            loadingState = dataEvent.loadState.toUiModel(),
                            animeDetailsUi = dataEvent.data.toUiModel(numberFormatter),
                        )
                    }
                }
        }
    }

    override fun handleIntent(intent: AnimeDetailsUiIntent) {
        when (intent) {
            AnimeDetailsUiIntent.BackClicked -> onBackClicked()
        }
    }

    private fun onBackClicked() {
        viewModelScope.launch {
            sendEffect(AnimeDetailsUiEffect.NavigateBack)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(animeId: Int): AnimeDetailsViewModel
    }

    companion object {

        fun provideFactory(
            assistedFactory: Factory,
            animeId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(animeId) as T
            }
        }
    }
}
