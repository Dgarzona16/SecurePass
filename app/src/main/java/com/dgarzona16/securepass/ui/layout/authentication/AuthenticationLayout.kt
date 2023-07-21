package com.dgarzona16.securepass.ui.layout.authentication

import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.enums.ErrorType
import com.dgarzona16.securepass.enums.status.AuthStatus
import com.dgarzona16.securepass.models.ErrorModel
import com.dgarzona16.securepass.ui.component.authenticate.Authentication
import com.dgarzona16.securepass.ui.component.authenticate.createPrompt
import com.dgarzona16.securepass.ui.component.authenticate.verifyAuthentication

@Composable
fun AuthenticationLayout(
    onNavigateToMain: () -> Unit,
    onNavigateToError: (ErrorModel) -> Unit
){
    var canAuth = false
    lateinit var promptInfo: BiometricPrompt.PromptInfo
    var auth by remember { mutableStateOf(AuthStatus.AWAITING_AUTH) }

    when (auth) {
        AuthStatus.AWAITING_AUTH -> {
            if (verifyAuthentication()){
                canAuth = true
                promptInfo = createPrompt(
                    stringResource(R.string.authentication_title),
                    stringResource(R.string.authentication_body)
                )
            }
            if (canAuth){
                Authentication(
                    onAuth = { auth = it },
                    promptInfo = promptInfo
                )
            }else{
                onNavigateToError(
                    ErrorModel(
                        title = stringResource(R.string.no_biometric_title),
                        message = stringResource(R.string.no_biometric_body),
                        btnText = stringResource(R.string.continue_anyway),
                        errorType = ErrorType.NO_BIOMETRIC_ERROR
                    )
                )
            }
        }
        AuthStatus.AUTHENTICATED -> {
            onNavigateToMain()
        }
        else -> {
            onNavigateToError(
                ErrorModel(
                    title = stringResource(R.string.authentication_is_failed_title),
                    message = stringResource(R.string.authentication_is_failed_body),
                    btnText = stringResource(R.string.try_again),
                    errorType = ErrorType.AUTH_ERROR
                )
            )
        }
    }
}