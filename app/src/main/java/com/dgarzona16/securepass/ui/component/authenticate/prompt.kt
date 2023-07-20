package com.dgarzona16.securepass.ui.component.authenticate

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt

fun createPrompt(
    title: String,
    subtitle: String,
): BiometricPrompt.PromptInfo {
    return BiometricPrompt.PromptInfo.Builder()
        .setTitle(title)
        .setSubtitle(subtitle)
        .setAllowedAuthenticators(
            BiometricManager.Authenticators.BIOMETRIC_STRONG
                or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL)
        .build()
}