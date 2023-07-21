package com.dgarzona16.securepass.ui.layout.SetupInit

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgarzona16.securepass.data.entities.Configs
import com.dgarzona16.securepass.utils.PREF_FIRST_USE

@Composable
fun FirstInitLayout(
    viewModel: FirstInitViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit = {}
){
    Button(onClick = {
        viewModel.insertConfig(Configs(id = PREF_FIRST_USE, value = "false"))
        onNavigateToMain()
    }) {
        Text(text = "Go to main")
    }
}
