package com.dgarzona16.securepass.ui.component.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dgarzona16.securepass.enums.BackupsFrequency
import com.dgarzona16.securepass.ui.component.ui.RadioButtonSP

@Composable
fun FrequencySetting(
    action: (BackupsFrequency) -> Unit = {},
    actualFrequency: BackupsFrequency
) {
    var backupsFrequency by remember { mutableStateOf(actualFrequency) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        RadioButtonSP(
            selected = backupsFrequency == BackupsFrequency.ONE_DAY,
            onClick = {
                backupsFrequency = BackupsFrequency.ONE_DAY
                action(BackupsFrequency.ONE_DAY)
            },
            text = "1 day"
        )
        RadioButtonSP(
            selected = backupsFrequency == BackupsFrequency.THREE_DAYS,
            onClick = {
                backupsFrequency = BackupsFrequency.THREE_DAYS
                action(BackupsFrequency.THREE_DAYS)
            },
            text = "3 days"
        )
        RadioButtonSP(
            selected = backupsFrequency == BackupsFrequency.FIVE_DAYS,
            onClick = {
                backupsFrequency = BackupsFrequency.FIVE_DAYS
                action(BackupsFrequency.FIVE_DAYS)
            },
            text = "5 days"
        )
        RadioButtonSP(
            selected = backupsFrequency == BackupsFrequency.SEVEN_DAYS,
            onClick = {
                backupsFrequency = BackupsFrequency.SEVEN_DAYS
                action(BackupsFrequency.SEVEN_DAYS)
            },
            text = "7 days"
        )
    }
}