package com.dgarzona16.securepass.ui.layout.SetupInit

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.data.entities.Configs
import com.dgarzona16.securepass.enums.BackupsFrequency
import com.dgarzona16.securepass.enums.status.PermissionStatus
import com.dgarzona16.securepass.models.BackupOptions
import com.dgarzona16.securepass.permissions.permissionManager
import com.dgarzona16.securepass.ui.component.ui.DividerSP
import com.dgarzona16.securepass.ui.component.ui.DropDownMenuSP
import com.dgarzona16.securepass.ui.component.ui.KeyboardType
import com.dgarzona16.securepass.ui.component.ui.TextFieldSP
import com.dgarzona16.securepass.utils.PREF_ACCOUNT_BACKUP
import com.dgarzona16.securepass.utils.PREF_BACKUP_IN_DAY
import com.dgarzona16.securepass.utils.PREF_FIRST_USE
import com.dgarzona16.securepass.utils.PREF_MASTER_PASSWORD
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun FirstInitLayout(
    viewModel: SetupInitViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit = {}
){
    val permission = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    var getPermission by remember { mutableStateOf(permission.status.isGranted) }
    val permissionGranted = remember { mutableStateOf(PermissionStatus.DENIED_FOREVER) }
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val isEmailValid = remember { mutableStateOf(false) }
    val frequency = remember { mutableStateOf(BackupsFrequency.DAILY) }

    val frequencyOptions = listOf(
        BackupOptions(stringResource(R.string.daily), BackupsFrequency.DAILY),
        BackupOptions(stringResource(R.string.weekly), BackupsFrequency.WEEKLY),
        BackupOptions(stringResource(R.string.monthly), BackupsFrequency.MONTHLY)
    )

    LaunchedEffect( email.value ){
        if (email.value != "") {
            isEmailValid.value = ! android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome_to_securepass),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(R.string.setup_body_text),
            style = MaterialTheme.typography.bodyLarge
        )

        if (!getPermission) {
            Text(
                text = "give permission to access your storage",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )
            Button(onClick = { getPermission = true }) {
                Text(text = "give permission")
            }
        } else {
            permissionGranted.value = permissionManager(
                permission = Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }

        Text(
            text = stringResource(R.string.import_label),
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = { /* TODO */ }) {
            Text(text = stringResource(R.string.import_btn))
        }

        DividerSP(text = stringResource(R.string.divider_label_or))

        Text(
            text = stringResource(R.string.setup_email_label),
            style = MaterialTheme.typography.bodyLarge
        )
        TextFieldSP(
            value = email,
            isError = isEmailValid,
            supportText = mutableStateOf(if (isEmailValid.value) stringResource(R.string.setup_email_error) else "")
        )

        Text(
            text = stringResource(R.string.setup_password_label),
            style = MaterialTheme.typography.bodyLarge
        )
        TextFieldSP(
            value = password,
            type = KeyboardType.Password
        )

        Text(
            text = stringResource(R.string.setup_frequency_backup),
            style = MaterialTheme.typography.bodyLarge
        )
        DropDownMenuSP(
            items = frequencyOptions,
            onSelected = { frequency.value = it.frequency },
            optionName = { it.string },
        )
        Button(onClick = {
            if ( ! isEmailValid.value && password.value != "" && permissionGranted.value != PermissionStatus.DENIED_FOREVER ){
                viewModel.insertConfig(Configs(id = PREF_ACCOUNT_BACKUP, value = email.value))
                viewModel.insertConfig(Configs(id = PREF_MASTER_PASSWORD, value = password.value))
                viewModel.insertConfig(Configs(id = PREF_BACKUP_IN_DAY, value = frequency.value.name))
                viewModel.insertConfig(Configs(id = PREF_FIRST_USE, value = "false"))
                onNavigateToMain()
            }
        }) {
            Text(text = stringResource(R.string.finish_setup))
        }
    }
}
