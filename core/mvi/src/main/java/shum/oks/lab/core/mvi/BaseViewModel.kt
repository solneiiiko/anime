/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<
    STATE : UiState,
    INTENT : UiIntent,
    ACTION : UiAction,
>(
    initialState: STATE,
    initialIntent: INTENT? = null,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _action = Channel<ACTION>(Channel.BUFFERED)
    val action: Flow<ACTION> = _action.receiveAsFlow()

    init {
        initialIntent?.let {
            viewModelScope.launch { handleIntent(it) }
        }
    }

    protected abstract suspend fun handleIntent(intent: INTENT)

    protected fun updateState(updater: STATE.() -> STATE) {
        _state.update { it.updater() }
    }

    protected suspend fun sendAction(action: ACTION) {
        _action.send(action)
    }
}
