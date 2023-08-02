package com.dgarzona16.securepass.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dgarzona16.securepass.ui.screens.application.AddApplicationScreen
import com.dgarzona16.securepass.ui.screens.application.ApplicationScreen
import com.dgarzona16.securepass.utils.ApplicationsGraph
import com.dgarzona16.securepass.utils.MainRoute

fun NavGraphBuilder.applicationsGraph(navController: NavController){
    navigation(
        route = MainRoute.APPLICATION,
        startDestination = ApplicationsGraph.APPLICATION_LIST
    ){
        composable(ApplicationsGraph.APPLICATION_LIST) {
            ApplicationScreen(
                navController = navController
            )
        }
        composable(ApplicationsGraph.APPLICATION_DETAIL){ /* TODO */ }
        composable(ApplicationsGraph.APPLICATION_ADD){ AddApplicationScreen(
            navController = navController
        ) }
    }
}