package com.example.sculptor

/**
 * Use this to initialise your theme. best done inside the application's onCreate method.
 */
object SculptorThemeEngine {
    fun initialise(theme: SLPTheme) {
        SCPThemeManager.loadTheme(theme)
    }
}
