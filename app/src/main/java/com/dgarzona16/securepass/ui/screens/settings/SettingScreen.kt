package com.dgarzona16.securepass.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgarzona16.securepass.ui.component.setting.ThemeSelectionOptions
import com.dgarzona16.securepass.ui.theme.ThemeState

@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    themeState: ThemeState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "escoger tema",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        ThemeSelectionOptions(
            viewModel = viewModel,
            themeState = themeState
        )
    }
}