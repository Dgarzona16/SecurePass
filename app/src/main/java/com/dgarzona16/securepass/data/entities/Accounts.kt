package com.dgarzona16.securepass.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "account")
data class Accounts(
    @PrimaryKey
    val id: UUID,
    val tag: String,
    val username: String,
    val password: String,
    val applicationId: UUID,
)
