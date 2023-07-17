package com.dgarzona16.securepass

import android.app.Application
import android.content.Context
import com.dgarzona16.securepass.utils.*
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class SecurePassApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(PREF_FIRST_USE, true)) {
            sharedPreferences.getString(PREF_THEME, "system")

        }
    }
}