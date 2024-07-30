package com.example.core.corestate

import kotlinx.coroutines.flow.StateFlow

interface Store<State, Action> {
    val dispatch: Dispatch<Action>
    val state: StateFlow<State>
}
