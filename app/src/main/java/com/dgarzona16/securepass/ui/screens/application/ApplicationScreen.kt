package com.dgarzona16.securepass.ui.screens.application

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dgarzona16.securepass.ui.component.application.ApplicationList
import com.dgarzona16.securepass.ui.component.ui.FAB
import com.dgarzona16.securepass.utils.ApplicationsGraph

@Composable
fun ApplicationScreen (
    applicationViewModel: ApplicationViewModel = hiltViewModel(),
    navController: NavController,
) {
    Log.d("ApplicationScreen", "Application list: ${applicationViewModel.applications}")
    ApplicationList(applications = applicationViewModel.applications)
    FAB(
        onClick = {
            navController.navigate(ApplicationsGraph.APPLICATION_ADD){}
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add"
        )
    }
}