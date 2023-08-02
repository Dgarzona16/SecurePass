package com.dgarzona16.securepass.ui.layout.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.enums.status.GetConfigStatus
import com.dgarzona16.securepass.navigation.AppNavigation
import com.dgarzona16.securepass.ui.component.ui.TopAppbar
import com.dgarzona16.securepass.ui.theme.SecurePassTheme
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private val themeState = ThemeState()
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecurePassTheme(themeState = themeState) {
                App(themeState = themeState)
            }
        }
    }
}

@Composable
fun App(themeState: ThemeState){
    val navController = rememberNavController()
    Main(themeState = themeState, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    viewModel: ActivityViewModel = hiltViewModel(),
    themeState: ThemeState,
    navController: NavHostController
){
    var loaded by remember { mutableStateOf(false) }
    var showTopBar by remember { mutableStateOf(false) }
    var isFirstUse by remember { mutableStateOf(true) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

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

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(loaded) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppbar(
                        navController = navController,
                        title = stringResource(id = R.string.app_name),
                        settingNavigation = {
                            navController.navigate(MainRoute.SETTING) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    AppNavigation(
                        themeState = themeState,
                        navController = navController,
                        startDestination = if (isFirstUse) AppRoute.SETUP_INITIAL else AppRoute.AUTHENTICATION,
                    )
                }
            }
        }else
            CircularProgressIndicator()
    }
}