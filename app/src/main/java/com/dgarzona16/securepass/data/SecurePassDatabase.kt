package com.dgarzona16.securepass.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dgarzona16.securepass.data.dao.AccountsDao
import com.dgarzona16.securepass.data.dao.ApplicationsDao
import com.dgarzona16.securepass.data.dao.ConfigsDao
import com.dgarzona16.securepass.data.entities.Accounts
import com.dgarzona16.securepass.data.entities.Applications
import com.dgarzona16.securepass.data.entities.Configs

@Database(
    entities = [
        Applications::class,
        Accounts::class,
        Configs::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SecurePassDatabase : RoomDatabase() {
    abstract fun applicationsDao(): ApplicationsDao
    abstract fun accountsDao(): AccountsDao
    abstract fun configsDao(): ConfigsDao
}