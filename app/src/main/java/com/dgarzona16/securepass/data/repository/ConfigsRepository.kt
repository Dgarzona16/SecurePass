package com.dgarzona16.securepass.data.repository

import com.dgarzona16.securepass.data.dao.ConfigsDao
import com.dgarzona16.securepass.data.entities.Configs
import javax.inject.Inject

class ConfigsRepository @Inject constructor(
    private val configsDao: ConfigsDao
) {
    suspend fun getConfig(key: String) = configsDao.getConfig(key)

    suspend fun getAllConfigs() = configsDao.getAllConfigs()

    suspend fun insertConfig(key: String, value: String) = configsDao.insert(Configs(key, value))
}