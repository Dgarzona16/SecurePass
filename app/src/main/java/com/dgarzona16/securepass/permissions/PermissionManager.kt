package com.dgarzona16.securepass.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.dgarzona16.securepass.enums.status.PermissionStatus
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun permissionManager(permission: String): PermissionStatus {
    val permissionState = rememberPermissionState(permission = permission)
    LaunchedEffect(Unit){
        permissionState.launchPermissionRequest()
    }
    return when {
        permissionState.status.isGranted -> {
            PermissionStatus.GRANTED
        }
        permissionState.status.shouldShowRationale -> {
            PermissionStatus.DENIED
        }
        else -> {
            PermissionStatus.DENIED_FOREVER
        }
    }
}