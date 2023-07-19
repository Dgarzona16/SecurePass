package com.dgarzona16.securepass.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Configs(
    @PrimaryKey
    val id: String,
    var value: String
)
