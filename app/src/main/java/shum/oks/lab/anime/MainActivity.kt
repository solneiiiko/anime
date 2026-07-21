/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.anime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import shum.oks.lab.anime.di.AppComponentHolder
import shum.oks.lab.anime.mvi.AppUiState
import shum.oks.lab.anime.mvi.AppViewModel
import shum.oks.lab.anime.navigation.AppNavDisplay
import shum.oks.lab.common.theme.AnimeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = viewModel(factory = AppComponentHolder.get().appViewModelFactory)
            val state by appViewModel.state.collectAsStateWithLifecycle()
            splashScreen.setKeepOnScreenCondition { state is AppUiState.Loading }
            when (val state = state) {
                AppUiState.Loading -> {
                    // Nothing to do. ^_^__/
                }
                is AppUiState.Success -> {
                    AnimeTheme(
                        themeMode = state.themeMode,
                        themeContrast = state.themeContrast,
                    ) {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            AppNavDisplay(
                                navButtons = state.navigationButtons,
                                modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
                            )
                        }
                    }
                }
            }
        }
    }
}
