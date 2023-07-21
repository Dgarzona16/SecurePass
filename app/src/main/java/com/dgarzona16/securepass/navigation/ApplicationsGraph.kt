package com.dgarzona16.securepass.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dgarzona16.securepass.ui.screens.application.ApplicationScreen
import com.dgarzona16.securepass.utils.ApplicationsGraph
import com.dgarzona16.securepass.utils.MainRoute

fun NavGraphBuilder.applicationsGraph(navController: NavController, appNavController: NavController){
    navigation(
        startDestination = ApplicationsGraph.APPLICATION_LIST,
        route = MainRoute.APPLICATION
    ){
        composable(ApplicationsGraph.APPLICATION_LIST) {
            ApplicationScreen(
                navController = navController,
                appNavController = appNavController
            )
        }
        composable(ApplicationsGraph.APPLICATION_DETAIL){ /* TODO */ }
    }
}