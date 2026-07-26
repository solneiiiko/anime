/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import shum.oks.lab.feature.details.anime.di.AnimeDetailsUiComponentHolder
import shum.oks.lab.feature.details.anime.mvi.AnimeDetailsViewModel
import shum.oks.lab.feature.details.anime.screens.AnimeDetailScreen


fun EntryProviderScope<NavKey>.animeDetailsProviders(
    navigator: AnimeDetailsNavigator,
) {
    entry<AnimeDetailsKey> { key ->
        AnimeDetailScreen(
            viewModel = viewModel(
                factory = AnimeDetailsViewModel.provideFactory(
                    assistedFactory = AnimeDetailsUiComponentHolder.get().viewModelAssistedFactory,
                    animeId = key.animeId
                )
            ),
            navigator = navigator,
        )
    }
}

@Serializable
data class AnimeDetailsKey(
    val animeId: Int,
) : NavKey
