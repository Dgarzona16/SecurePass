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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dgarzona16.securepass.enums.ThemeSelection
import com.dgarzona16.securepass.permissions.permissionManager
import com.dgarzona16.securepass.ui.component.TopAppbar
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

        setContent {
            SecurePassTheme(themeState = themeState) {
                if (isFirstUse) {
                    //TODO: layout for first use
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    ) {
                        Text(text = "Initial Setup")
                        Button(onClick = { sharedPreferences.edit().putBoolean(PREF_FIRST_USE, false).apply() }) {
                            Text(text = "Request Permission")
                        }
                    }
                } else {
                    //TODO: layout for main screen
                    MainLayout(themeState = themeState) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(16.dp)
                        ) {
                            Text(text = "Theme Selection")
                            ThemeSelectionOptions(themeState = themeState)
                            Button(onClick = { toast("Hola") }) {
                                Text(text = "Request Permission")
                            }
                        }
                    }
                }
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
            .fillMaxWidth()
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