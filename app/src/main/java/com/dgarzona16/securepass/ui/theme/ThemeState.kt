package com.dgarzona16.securepass.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.dgarzona16.securepass.enums.ThemeSelection

class ThemeState {
    private val _themeSelection = mutableStateOf(ThemeSelection.SYSTEM)
    val themeSelection: State<ThemeSelection> = _themeSelection

    val isDarkTheme =  mutableStateOf(false)

    val useSystemTheme = mutableStateOf(true)

    fun setThemeSelection(selection: ThemeSelection) {
        _themeSelection.value = selection
        when (selection) {
            ThemeSelection.LIGHT -> {
                isDarkTheme.value = false
                useSystemTheme.value = false
            }
            ThemeSelection.DARK -> {
                isDarkTheme.value = true
                useSystemTheme.value = false
            }
            ThemeSelection.SYSTEM -> {
                isDarkTheme.value = false
                useSystemTheme.value = true
            }
        }
    }
}