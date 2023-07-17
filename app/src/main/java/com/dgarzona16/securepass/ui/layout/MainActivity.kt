package com.dgarzona16.securepass.ui.layout

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.dgarzona16.securepass.ui.theme.SecurePassTheme
import com.dgarzona16.securepass.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstUse = sharedPreferences.getBoolean(PREF_FIRST_USE, true)
        val isBiometricEnabled = sharedPreferences.getBoolean(PREF_BIOMETRIC_ENABLED, false)

        setContent {
            SecurePassTheme {
                if (isFirstUse) {
                    //temporary
                    Text(text ="First use")
                    Button(onClick = { sharedPreferences.edit().putBoolean(PREF_FIRST_USE, false).apply() }) {
                        Text(text = "Save")
                    }
                }else{
                    //temporary
                    if (isBiometricEnabled) {
                        Text(text = "Biometric enabled")
                    }else{
                        Text(text = "Biometric disabled")
                    }
                }
            }
        }
    }
}