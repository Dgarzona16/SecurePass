package com.dgarzona16.securepass.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.navigation.NavMain
import com.dgarzona16.securepass.ui.component.ui.TopAppbar
import com.dgarzona16.securepass.ui.theme.ThemeState
import com.dgarzona16.securepass.utils.MainNav

@Composable
fun MainLayout(
    themeState: ThemeState
) {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()){
        TopAppbar(
            navController = navController,
            title = stringResource(id = R.string.app_name),
            settingNavigation = { navController.navigate(MainNav.SETTING) }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            NavMain(
                themeState = themeState,
                navController = navController
            )
        }
    }
}