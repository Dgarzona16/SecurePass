package com.dgarzona16.securepass.ui.layout.firstInit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgarzona16.securepass.data.dao.ConfigsDao
import com.dgarzona16.securepass.data.entities.Configs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstInitViewModel @Inject constructor(
    private val configsDao: ConfigsDao
): ViewModel() {

    fun insertConfig(config: Configs) {
        viewModelScope.launch {
            configsDao.insert(config)
        }
    }

    //fun getConfig(key: String) = configsDao.getConfig(key)
}