package com.dgarzona16.securepass.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dgarzona16.securepass.ui.screens.settings.SettingScreen
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.ApplicationsGraph
import com.dgarzona16.securepass.utils.MainRoute

@Composable
fun NavMain(
    innerPadding: PaddingValues,
    themeState: ThemeState,
    navController: NavHostController
){
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp),
    ) {
        NavHost(
            route = MainRoute.MAIN,
            navController = navController,
            startDestination = MainRoute.APPLICATION,
        ) {
            applicationsGraph(navController = navController)

            composable(MainRoute.ACCOUNT) { /*TODO*/ }
            composable(MainRoute.SETTING) { SettingScreen(themeState = themeState) }
        }
    }
}