/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<
    STATE : UiState,
    INTENT : UiIntent,
    EFFECT : UiEffect,
>(
    initialState: STATE,
    private val onClearedCallback: (() -> Unit)?,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    // TODO handle with double click! https://github.com/solneiiiko/anime/issues/45
    private val _effect = Channel<EFFECT>(Channel.BUFFERED)
    val effect: Flow<EFFECT> = _effect.receiveAsFlow()

    abstract fun handleIntent(intent: INTENT)

    protected fun updateState(updater: STATE.() -> STATE) {
        _state.update { it.updater() }
    }

    protected suspend fun sendEffect(effect: EFFECT) {
        _effect.send(effect)
    }

    override fun onCleared() {
        onClearedCallback?.invoke()
    }
}
