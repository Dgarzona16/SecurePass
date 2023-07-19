package com.dgarzona16.securepass.ui.layout.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgarzona16.securepass.enums.status.GetConfigStatus
import com.dgarzona16.securepass.navigation.NavInitToMain
import com.dgarzona16.securepass.ui.theme.SecurePassTheme
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Main()
        }
    }
}

@Composable
fun Main(viewModel: ActivityViewModel = hiltViewModel()){
    val themeState = ThemeState()
    var loaded by remember { mutableStateOf(false) }
    var isFirstUse by remember { mutableStateOf(true) }

    viewModel.getAllConfigs()

    LaunchedEffect(viewModel.configsStatus.value){
        viewModel.apply {
            if(configsStatus.value == GetConfigStatus.SUCCESS){
                //remove loading effect
                loaded = true
                //set initial screen
                val conf = config.first { it.id == PREF_FIRST_USE }
                isFirstUse = conf.value.toBoolean()
            }
        }
    }

    SecurePassTheme(themeState = themeState) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            if(loaded){
                NavInitToMain(
                    themeState = themeState,
                    startDestination = if(isFirstUse) InitialNav.FIRST_INIT else InitialNav.MAIN
                )
            }else{
                CircularProgressIndicator()
            }
        }
    }
}