package com.dgarzona16.securepass.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.enums.status.AuthStatus
import com.dgarzona16.securepass.navigation.MainNavigation
import com.dgarzona16.securepass.ui.component.ui.TopAppbar
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.MainRoute
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    appNavController: NavHostController,
    themeState: ThemeState
) {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppbar(
                navController = navController,
                title = stringResource(id = R.string.app_name),
                settingNavigation = { navController.navigate(MainRoute.SETTING){ launchSingleTop = true } }
            )
        }
    ){ innerPadding ->
        MainNavigation(
            innerPadding = innerPadding,
            themeState = themeState,
            navController = navController,
            appNavController = appNavController
        )
    }
}