package com.dgarzona16.securepass.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dgarzona16.securepass.data.entities.Accounts
import com.dgarzona16.securepass.data.entities.Applications

@Database(
    entities = [
        Applications::class,
        Accounts::class
       ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SecurePassDatabase : RoomDatabase() {

}