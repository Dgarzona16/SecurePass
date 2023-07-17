package com.dgarzona16.securepass.ui.layout

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dgarzona16.securepass.enums.ThemeSelection
import com.dgarzona16.securepass.permissions.PermissionManager
import com.dgarzona16.securepass.ui.theme.SecurePassTheme
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val themeState = ThemeState()
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstUse = sharedPreferences.getBoolean(PREF_FIRST_USE, true)
        val isBiometricEnabled = sharedPreferences.getBoolean(PREF_BIOMETRIC_ENABLED, false)

        setContent {
            SecurePassTheme(themeState = themeState) {
                PermissionManager(Manifest.permission.WRITE_EXTERNAL_STORAGE,)
            }
        }
    }
}


//TODO: Remove this function and move to configuration screen
@Composable
fun ThemeSelectionOptions(themeState: ThemeState) {
    val selectedTheme = themeState.themeSelection.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        RadioButton(
            selected = selectedTheme == ThemeSelection.LIGHT,
            onClick = { themeState.setThemeSelection(ThemeSelection.LIGHT) }
        )

        RadioButton(
            selected = selectedTheme == ThemeSelection.DARK,
            onClick = { themeState.setThemeSelection(ThemeSelection.DARK) }
        )

        RadioButton(
            selected = selectedTheme == ThemeSelection.SYSTEM,
            onClick = { themeState.setThemeSelection(ThemeSelection.SYSTEM) }
        )
    }
}