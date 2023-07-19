package com.dgarzona16.securepass.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dgarzona16.securepass.utils.InitialNav
import com.dgarzona16.securepass.ui.layout.firstInit.FirstInitLayout
import com.dgarzona16.securepass.ui.layout.MainLayout
import com.dgarzona16.securepass.ui.theme.ThemeState

@Composable
fun NavInitToMain(
    navController: NavHostController = rememberNavController(),
    startDestination: String = InitialNav.FIRST_INIT,
    themeState: ThemeState,
){
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(InitialNav.FIRST_INIT){ FirstInitLayout(
            onNavigateToMain = { navController.navigate("main"){ popUpTo(0) } }
        ) }
        composable(InitialNav.MAIN){ MainLayout(themeState = themeState) }
    }
}