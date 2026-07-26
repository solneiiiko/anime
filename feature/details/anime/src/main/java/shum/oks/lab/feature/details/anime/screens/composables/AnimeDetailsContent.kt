/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.feature.details.anime.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import shum.oks.lab.feature.details.anime.screens.models.AnimeDetailsUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnimeDetailsContent(
    animeDetailsUi: AnimeDetailsUi,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier.fillMaxSize()) {
        ExpandedAnimeHeader(
            headerInfoUi = animeDetailsUi.headerInfo,
            onBackClicked = onBackClicked,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = animeDetailsUi.toString(),
        )
    }
}
