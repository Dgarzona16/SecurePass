package com.dgarzona16.securepass.ui.layout.SetupInit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgarzona16.securepass.data.entities.Configs
import com.dgarzona16.securepass.enums.BackupsFrequency
import com.dgarzona16.securepass.ui.component.setting.FrequencySetting
import com.dgarzona16.securepass.ui.component.ui.TextFieldSP
import com.dgarzona16.securepass.utils.PREF_FIRST_USE

@Composable
fun FirstInitLayout(
    viewModel: SetupInitViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit = {}
){
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    //TODO: add components to setup the app
    /*
    * 1. Add a password
    * 2. Add a security email
    */
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add your secure password to encrypt your backup")
        TextFieldSP(label = null, value = password)
        Text(text = "Add your email to generate a backups")
        TextFieldSP(label = null,value = email)
        Text(text = "Add your frequency to generate a backups")
        FrequencySetting(
            actualFrequency = BackupsFrequency.SEVEN_DAYS
        )
        Button(onClick = {
            viewModel.insertConfig(Configs(id = PREF_FIRST_USE, value = "false"))
            onNavigateToMain()
        }) {
            Text(text = "Go to main")
        }
    }
}
