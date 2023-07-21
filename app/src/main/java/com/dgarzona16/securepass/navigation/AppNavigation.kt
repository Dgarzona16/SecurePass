package com.dgarzona16.securepass.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.enums.ErrorType
import com.dgarzona16.securepass.utils.AppRoute
import com.dgarzona16.securepass.ui.layout.SetupInit.FirstInitLayout
import com.dgarzona16.securepass.ui.layout.MainLayout
import com.dgarzona16.securepass.ui.layout.authentication.AuthenticationLayout
import com.dgarzona16.securepass.ui.screens.error.ErrorScreen
import com.dgarzona16.securepass.ui.theme.ThemeState

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoute.SETUP_INITIAL,
    themeState: ThemeState,
){

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ){

        composable(AppRoute.SETUP_INITIAL){
            FirstInitLayout(
                onNavigateToMain = {
                    navController.navigate("main"){ popUpTo(0) }
                }
            )
        }
        composable(
            route = "${AppRoute.ERROR}/{title}/{message}/{btnText}/{errorType}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("message") { type = NavType.StringType },
                navArgument("btnText") { type = NavType.StringType },
                navArgument("errorType") { type = NavType.StringType },
            )
        ){
            val title = it.arguments?.getString("title") ?: "Error"
            val message = it.arguments?.getString("message") ?: "This is an error"
            val btnText = it.arguments?.getString("btnText") ?: "Ok"
            val errorType = ErrorType.valueOf(it.arguments?.getString("errorType") ?: "ANOTHER_ERROR")
            val onAction = {
                when(errorType){
                     ErrorType.AUTH_ERROR -> navController.navigate(AppRoute.AUTHENTICATION)
                    else -> navController.navigate(AppRoute.MAIN)
                }
            }
            ErrorScreen(
                title = title,
                message = message,
                btnText = btnText,
                onAction = onAction
            )
        }
        composable(AppRoute.AUTHENTICATION){
            AuthenticationLayout(
                onNavigateToMain = {
                    navController.navigate(AppRoute.MAIN){ popUpTo(0) }
                },
                onNavigateToError = {
                    navController.navigate(
                        "${AppRoute.ERROR}/${it.title}/${it.message}/${it.btnText}/${it.errorType.name}"
                    ){ popUpTo(0) }
                }
            )
        }
        composable(AppRoute.MAIN){
            MainLayout(
                appNavController = navController,
                themeState = themeState
            )
        }
    }
}