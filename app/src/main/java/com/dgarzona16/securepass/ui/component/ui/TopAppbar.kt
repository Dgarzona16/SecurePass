package com.dgarzona16.securepass.ui.component.ui

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.dgarzona16.securepass.utils.AppRoute
import com.dgarzona16.securepass.utils.ApplicationsGraph
import com.dgarzona16.securepass.utils.MainRoute
import com.dgarzona16.securepass.utils.showTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(
    navController: NavController,
    title: String,
    settingNavigation: () -> Unit,
) {
    var showTopBar by remember{ mutableStateOf(false) }
    val backStackEntryFlow = navController.currentBackStackEntryFlow
    val backStackEntryState by backStackEntryFlow.collectAsState(initial = null)
    val hasBackStackEntry = backStackEntryState != null


    LaunchedEffect(navController.currentDestination?.route){
        Log.i("TopAppbar", "navController.currentDestination?.route: ${navController.currentDestination?.route}")
        showTopBar = showTopBar(navController.currentDestination?.route)
    }

    if (showTopBar){
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (
                    hasBackStackEntry
                    && navController.currentDestination?.route != ApplicationsGraph.APPLICATION_LIST
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = settingNavigation) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        )
    }
}