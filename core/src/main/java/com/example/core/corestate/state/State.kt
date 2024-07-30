package com.example.core.corestate.state

/**
 * State classes.
 */
sealed class State<T> {
    class Loading<T> : State<T>()

    class Failed<T>(val errorMessage: String) : State<T>()

    class Uninitialised<T> : State<T>()

    class Loaded<T>(val data: T) : State<T>()
}
