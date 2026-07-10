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
import shum.oks.lab.anime.usecases.GetAppSettingUseCase
import shum.oks.lab.core.mvi.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

internal class AppViewModel @Inject constructor(
    private val getAppSettingsUseCase: GetAppSettingUseCase,
) : BaseViewModel<
    AppUiState,
    AppUiIntent,
    AppUiEffect
>(
    initialState = AppUiState.Loading,
    initialIntent = AppUiIntent.LoadAppSettings,
    onClearedCallback = null,
) {

    override suspend fun handleIntent(intent: AppUiIntent) {
        when (intent) {
            AppUiIntent.LoadAppSettings -> {
                loadAppSettings()
            }
        }
    }

    private suspend fun loadAppSettings() {
        val appSettings = getAppSettingsUseCase()
        updateState {
            AppUiState.Success(
                themeMode = appSettings.themeMode,
                contrastMode = appSettings.contrastMode
            )
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
