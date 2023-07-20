package com.dgarzona16.securepass.ui.component.authenticate

import androidx.biometric.BiometricManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.dgarzona16.securepass.ui.layout.activity.MainActivity
@Composable
fun verifyAuthentication(): Boolean {
    return BiometricManager
        .from(LocalContext.current as MainActivity)
        .canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG
                    or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        ) == BiometricManager.BIOMETRIC_SUCCESS
}