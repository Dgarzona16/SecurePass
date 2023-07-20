package com.dgarzona16.securepass.ui.screens.application

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dgarzona16.securepass.ui.component.application.ApplicationList
import com.dgarzona16.securepass.ui.component.ui.FAB

@Composable
fun ApplicationScreen (
    navigation: NavController
) {
    ApplicationList(applications = emptyList())
    FAB(
        onClick = { /* TODO: */ }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add"
        )
    }
}

@Preview
@Composable
fun ApplicationScreenPreview() {
    val navController = NavController(LocalContext.current)
    ApplicationScreen(navController)
}