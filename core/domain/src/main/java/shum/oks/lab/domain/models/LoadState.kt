/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.domain.models

sealed interface LoadState {

    data object Loading : LoadState

    data object Success : LoadState

    data class Error(
        val throwable: Throwable,
    ) : LoadState
}
