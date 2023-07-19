package com.dgarzona16.securepass.ui.component.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dgarzona16.securepass.enums.ThemeSelection
import com.dgarzona16.securepass.ui.component.ui.RadioButtonSP
import com.dgarzona16.securepass.ui.screens.settings.SettingViewModel
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.PREF_THEME


@Composable
fun ThemeSelectionOptions(
    viewModel: SettingViewModel,
    themeState: ThemeState
) {
    val selectedTheme = themeState.themeSelection.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RadioButtonSP(
            selected = selectedTheme == ThemeSelection.LIGHT,
            onClick = {
                themeState.setThemeSelection(ThemeSelection.LIGHT)
                viewModel.insertConfig(PREF_THEME, ThemeSelection.LIGHT.name)
            },
            text = "Light"
        )
        RadioButtonSP(
            selected = selectedTheme == ThemeSelection.DARK,
            onClick = {
                themeState.setThemeSelection(ThemeSelection.DARK)
                viewModel.insertConfig(PREF_THEME, ThemeSelection.DARK.name)
            },
            text = "Dark"
        )
        RadioButtonSP(
            selected = selectedTheme == ThemeSelection.SYSTEM,
            onClick = {
                themeState.setThemeSelection(ThemeSelection.SYSTEM)
                viewModel.insertConfig(PREF_THEME, ThemeSelection.SYSTEM.name)
            },
            text = "System"
        )
    }
}