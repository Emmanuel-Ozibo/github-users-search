package com.example.core.corestate

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MVIStore<State, Action> : Store<State, Action> {
    private val dispatcher: Channel<Action> = Channel(Channel.UNLIMITED)

    override val dispatch: Dispatch<Action> = { action -> dispatcher.trySend(action) }

    override val state: StateFlow<State>

    constructor(
        coroutineScope: CoroutineScope,
        initialState: State,
        reducer: Reducer<State, Action>,
        sideEffects: List<SideEffect<State, Action>> = emptyList(),
    ) {
        state =
            dispatcher
                .receiveAsFlow()
                .scan(initialState) { state, action ->
                    reducer(state, action).also { newState ->
                        sideEffects.forEach { sideEffect ->
                            coroutineScope.launch {
                                sideEffect.invoke(dispatch, newState, action)
                            }
                        }
                    }
                }
                .stateIn(coroutineScope, SharingStarted.Eagerly, initialState)
    }

    constructor(
        coroutineScope: CoroutineScope,
        initialState: State,
        initialAction: Action?,
        reducer: Reducer<State, Action>,
        sideEffects: List<SideEffect<State, Action>> = emptyList(),
    ) : this(coroutineScope, initialState, reducer, sideEffects) {
        if (initialAction != null) {
            dispatch(initialAction)
        }
    }
}
