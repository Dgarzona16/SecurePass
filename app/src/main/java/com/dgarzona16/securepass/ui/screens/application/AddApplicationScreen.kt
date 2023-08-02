package com.dgarzona16.securepass.ui.screens.application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dgarzona16.securepass.ui.component.ui.TextFieldSP

@Composable
fun AddApplicationScreen(
    applicationViewModel: ApplicationViewModel = hiltViewModel(),
    navController: NavController
) {
    val applicationName = remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        TextFieldSP(value = applicationName)

        Button(onClick = {
            if (applicationName.value.isNotEmpty()) {
                applicationViewModel.addApplication(applicationName.value)
                navController.popBackStack()
            }
        }) {
            Text(text = "Add")
        }
    }
}