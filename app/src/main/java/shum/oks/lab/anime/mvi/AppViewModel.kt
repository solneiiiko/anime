/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shum.oks.lab.anime.mvi.mappers.toTopNavButtonUiList
import shum.oks.lab.anime.usecases.GetAppSettingUseCase
import shum.oks.lab.anime.usecases.GetTopNavButtonsUseCase
import shum.oks.lab.core.mvi.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

internal class AppViewModel @Inject constructor(
    private val getAppSettingsUseCase: GetAppSettingUseCase,
    private val getTopNavButtonsUseCase: GetTopNavButtonsUseCase
) : BaseViewModel<
    AppUiState,
    AppUiIntent,
    AppUiEffect
>(
    initialState = AppUiState.Loading,
    onClearedCallback = null,
) {

    init {
        handleIntent(AppUiIntent.LoadAppSettings)
    }

    override fun handleIntent(intent: AppUiIntent) {
        when (intent) {
            AppUiIntent.LoadAppSettings -> {
                loadAppSettings()
            }
        }
    }

    private fun loadAppSettings() {
        viewModelScope.launch {
            getAppSettingsUseCase().apply {
                updateState {
                    AppUiState.Success(
                        themeMode = themeMode,
                        themeContrast = themeContrast,
                        navigationButtons = getTopNavButtonsUseCase().toTopNavButtonUiList(),
                    )
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val provider: Provider<AppViewModel>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            provider.get() as T
    }
}
