package com.dgarzona16.securepass.models

import com.dgarzona16.securepass.enums.BackupsFrequency

data class BackupOptions(
    val string: String,
    val frequency: BackupsFrequency
)
