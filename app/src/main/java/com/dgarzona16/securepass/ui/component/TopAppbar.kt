package com.dgarzona16.securepass.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(
    navController: NavController,
    onBackAction: () -> Unit,
    title: String,
    settingNavigation: () -> Unit,
) {
    val backStackEntryFlow = navController.currentBackStackEntryFlow
    val backStackEntryState by backStackEntryFlow.collectAsState(initial = null)
    val hasBackStackEntry = backStackEntryState != null

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (hasBackStackEntry) {
                IconButton(onClick = onBackAction) {
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