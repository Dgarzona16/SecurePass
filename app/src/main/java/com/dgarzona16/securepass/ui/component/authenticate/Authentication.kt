package com.dgarzona16.securepass.ui.component.authenticate

import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.dgarzona16.securepass.enums.status.AuthStatus
import com.dgarzona16.securepass.ui.layout.activity.MainActivity

@Composable
fun Authentication(
    onAuth: (AuthStatus) -> Unit,
    promptInfo: PromptInfo
){
    BiometricPrompt(
        LocalContext.current as MainActivity,
        ContextCompat.getMainExecutor(LocalContext.current),
        object : BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
               onAuth(AuthStatus.AUTHENTICATED)
            }

            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                onAuth(AuthStatus.NOT_AUTHENTICATED)
            }
        }
    ).authenticate(promptInfo)
}