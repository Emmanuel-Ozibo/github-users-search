package com.example.sculptor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

internal object SCPThemeManager {
    private val defaultTheme = SLPTheme()

    private val currentTheme: MutableState<SLPTheme> = mutableStateOf(defaultTheme)

    fun loadTheme(slpTheme: SLPTheme) {
        currentTheme.value = slpTheme
    }

    fun getCurrentTheme() = currentTheme
}
