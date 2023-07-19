package com.dgarzona16.securepass.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dgarzona16.securepass.data.entities.Configs

@Dao
interface ConfigsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: Configs)

    @Query("SELECT * FROM Configs WHERE id = :key")
    suspend fun getConfig(key: String): Configs

    @Query("SELECT * FROM Configs")
    suspend fun getAllConfigs(): List<Configs>
}