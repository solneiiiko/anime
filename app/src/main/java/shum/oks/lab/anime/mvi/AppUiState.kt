/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime.mvi

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import shum.oks.lab.anime.mvi.models.TopNavButtonUi
import shum.oks.lab.core.mvi.UiState
import shum.oks.lab.entity.settings.theme.models.ThemeContrast
import shum.oks.lab.entity.settings.theme.models.ThemeMode

@Immutable
internal sealed interface AppUiState : UiState {

    data object Loading : AppUiState

    data class Success(
        val themeMode: ThemeMode,
        val themeContrast: ThemeContrast,
        val navigationButtons: ImmutableList<TopNavButtonUi>
    ) : AppUiState
}
