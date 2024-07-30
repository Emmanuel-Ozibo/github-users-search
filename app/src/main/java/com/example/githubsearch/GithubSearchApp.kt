package com.example.githubsearch

import android.app.Application
import com.example.sculptor.SLPTheme
import com.example.sculptor.SculptorThemeEngine
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class GithubSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SculptorThemeEngine.initialise(SLPTheme())
    }
}
