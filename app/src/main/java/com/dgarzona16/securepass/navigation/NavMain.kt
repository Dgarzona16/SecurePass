package com.dgarzona16.securepass.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dgarzona16.securepass.ui.screens.ApplicationScreen
import com.dgarzona16.securepass.ui.screens.settings.SettingScreen
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.MainNav

@Composable
fun NavMain(
    themeState: ThemeState,
    navController: NavHostController
){

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = MainNav.APPLICATION_LIST
    ){
        composable(MainNav.APPLICATION_LIST){ ApplicationScreen() }
        composable(MainNav.ACCOUNT_LIST){ /*TODO*/ }
        composable(MainNav.SETTING){ SettingScreen(themeState = themeState) }
    }
}