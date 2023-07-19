package com.dgarzona16.securepass.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.ui.component.TopAppbar
import com.dgarzona16.securepass.ui.theme.ThemeState

@Composable
fun MainLayout(
    themeState: ThemeState,
    content: @Composable () -> Unit,
    //TODO: Add navigation
) {
    val navController = NavController(LocalContext.current)
    Column(modifier = Modifier.fillMaxSize()){
        TopAppbar(
            navController = navController,
            onBackAction = { /*TODO*/ },
            title = stringResource(id = R.string.app_name),
            settingNavigation = { /*TODO*/ }
        )
        content()
    }
}