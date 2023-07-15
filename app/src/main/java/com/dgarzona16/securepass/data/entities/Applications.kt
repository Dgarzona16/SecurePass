package com.dgarzona16.securepass.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "application")
data class Applications(
    @PrimaryKey
    val id: UUID,
    val name: String,
)
