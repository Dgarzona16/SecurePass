package com.dgarzona16.securepass.data.repository

import com.dgarzona16.securepass.data.dao.ApplicationsDao
import com.dgarzona16.securepass.data.entities.Applications
import java.util.UUID
import javax.inject.Inject

class ApplicationsRepository @Inject constructor(
    private val applicationsDao: ApplicationsDao
){
    suspend fun getAllApplications() = applicationsDao.getAllApplications()

    suspend fun getApplicationById(id: UUID) = applicationsDao.getApplicationById(id)

    suspend fun insertApplication(name: String) = applicationsDao.insertApplication(Applications(UUID.randomUUID(), name))

    suspend fun deleteApplication(id: UUID) = applicationsDao.deleteApplication(id)
}