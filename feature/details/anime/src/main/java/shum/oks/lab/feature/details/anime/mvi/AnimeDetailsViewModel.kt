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
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import shum.oks.lab.core.mvi.BaseViewModel

internal class AnimeDetailsViewModel @AssistedInject constructor(
    @Assisted private val animeId: Int,
) : BaseViewModel<
    AnimeDetailsUiState,
    AnimeDetailsUiIntent,
    AnimeDetailsUiEffect,
>(
    initialState = AnimeDetailsUiState.Loading,
    onClearedCallback = {  } // TODO
) {

    override fun handleIntent(intent: AnimeDetailsUiIntent) {
        TODO("Not yet implemented")
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
