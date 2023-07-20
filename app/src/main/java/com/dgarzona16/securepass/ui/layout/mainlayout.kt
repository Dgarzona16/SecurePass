package com.dgarzona16.securepass.ui.layout

import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.enums.status.AuthStatus
import com.dgarzona16.securepass.navigation.NavMain
import com.dgarzona16.securepass.ui.component.authenticate.Authentication
import com.dgarzona16.securepass.ui.component.authenticate.createPrompt
import com.dgarzona16.securepass.ui.component.authenticate.verifyAuthentication
import com.dgarzona16.securepass.ui.component.ui.TopAppbar
import com.dgarzona16.securepass.ui.screens.error.ErrorScreen
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.MainRoute
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    themeState: ThemeState
) {
    var canAuth = false
    lateinit var promptInfo: PromptInfo
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var auth by remember { mutableStateOf(AuthStatus.AWAITING_AUTH) }
    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (auth == AuthStatus.AUTHENTICATED){
                TopAppbar(
                    navController = navController,
                    title = stringResource(id = R.string.app_name),
                    settingNavigation = { navController.navigate(MainRoute.SETTING){ launchSingleTop = true } }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){ innerPadding ->
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
                    ErrorScreen(
                        innerPadding = innerPadding,
                        title = stringResource(R.string.no_biometric_title),
                        message = stringResource(R.string.no_biometric_body),
                        btnText = stringResource(R.string.continue_anyway),
                        onAction = { auth = AuthStatus.AUTHENTICATED }
                    )
                }
            }
            AuthStatus.AUTHENTICATED -> {
                NavMain(
                    innerPadding = innerPadding,
                    themeState = themeState,
                    navController = navController
                )
            }
            else -> {
                ErrorScreen(
                    innerPadding = innerPadding,
                    title = stringResource(R.string.authentication_is_failed_title),
                    message = stringResource(R.string.authentication_is_failed_body),
                    btnText = stringResource(R.string.try_again),
                    onAction = { auth = AuthStatus.AWAITING_AUTH }
                )
            }
        }
    }
}