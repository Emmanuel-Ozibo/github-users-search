package com.example.users.utils

import com.example.core.corestate.Dispatch
import com.example.core.corestate.SideEffect

internal suspend fun <State, Action> runSideEffects(
    dispatch: Dispatch<Action>,
    state: State,
    event: Action,
    sideEffects: List<SideEffect<State, Action>>,
) {
    sideEffects.forEach { se ->
        se.invoke(dispatch, state, event)
    }
}
