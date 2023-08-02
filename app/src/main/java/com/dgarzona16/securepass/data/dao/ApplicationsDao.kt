package com.dgarzona16.securepass.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dgarzona16.securepass.data.entities.Applications
import java.util.UUID

@Dao
interface ApplicationsDao {
    @Query("SELECT * FROM application")
    suspend fun getAllApplications(): List<Applications>
    @Query("SELECT * FROM application WHERE id = :id")
    suspend fun getApplicationById(id: UUID): Applications
    @Insert
    suspend fun insertApplication(application: Applications)
    @Query("DELETE FROM application WHERE id = :id")
    suspend fun deleteApplication(id: UUID)
}