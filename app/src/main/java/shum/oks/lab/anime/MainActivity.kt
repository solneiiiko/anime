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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import shum.oks.lab.anime.di.AppComponentHolder
import shum.oks.lab.anime.mvi.AppUiState
import shum.oks.lab.anime.mvi.AppViewModel
import shum.oks.lab.anime.ui.theme.AnimeTheme
import shum.oks.lab.core.theme.models.ContrastMode
import shum.oks.lab.core.theme.models.ThemeMode

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel =
                viewModel(factory = AppComponentHolder.get().appViewModelFactory)
            val state by appViewModel.state.collectAsStateWithLifecycle()
            splashScreen.setKeepOnScreenCondition { state is AppUiState.Loading }
            when (val state = state) {
                AppUiState.Loading -> {
                    // ^_^__/
                    // TODO write logs ?
                }

                is AppUiState.Success -> {
                    AnimeTheme(
                        themeMode = state.themeMode,
                        contrastMode = state.contrastMode,
                    ) {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Greeting(
                                name = "Anime",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimeTheme(
        themeMode = ThemeMode.SYSTEM,
        contrastMode = ContrastMode.STANDARD,
    ) {
        Greeting("Anime")
    }
}