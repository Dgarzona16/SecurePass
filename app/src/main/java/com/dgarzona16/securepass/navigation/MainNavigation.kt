package com.dgarzona16.securepass.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dgarzona16.securepass.ui.screens.settings.SettingScreen
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.AppRoute
import com.dgarzona16.securepass.utils.MainRoute

fun NavGraphBuilder.mainNavigation(
    navController: NavController,
    themeState: ThemeState
){
    navigation(
        route = AppRoute.MAIN,
        startDestination = MainRoute.APPLICATION
    ){
        applicationsGraph(navController = navController)
        composable(MainRoute.ACCOUNT) { /*TODO*/ }
        composable(MainRoute.SETTING) { SettingScreen(themeState = themeState) }
    }
}