package com.example.core.corestate

typealias Reducer<State, Action> = (state: State, action: Action) -> State

typealias SideEffect<State, Action> = suspend Dispatch<Action>.(state: State, action: Action) -> Unit

typealias Dispatch<Action> = (Action) -> Unit
