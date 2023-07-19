package com.dgarzona16.securepass

import android.app.Application
import android.content.Context
import com.dgarzona16.securepass.data.dao.ConfigsDao
import com.dgarzona16.securepass.data.entities.Configs
import com.dgarzona16.securepass.enums.ThemeSelection
import com.dgarzona16.securepass.utils.*
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class SecurePassApplication: Application() {
    @Inject
    lateinit var configsDao: ConfigsDao
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(PREF_FIRST_USE, true)) {
            val theme = Configs(id = PREF_THEME, value = ThemeSelection.SYSTEM.name)
            val firstUse = Configs(id = PREF_FIRST_USE, value = true.toString())
            runBlocking {
                configsDao.insert(theme)
                configsDao.insert(firstUse)
            }
            sharedPreferences.edit().putBoolean(PREF_FIRST_USE, false).apply()
        }
    }
}