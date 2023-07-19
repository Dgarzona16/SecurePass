package com.dgarzona16.securepass.ui.layout.activity

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgarzona16.securepass.data.entities.Configs
import com.dgarzona16.securepass.data.repository.ConfigsRepository
import com.dgarzona16.securepass.enums.status.GetConfigStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: ConfigsRepository
): ViewModel() {
    val configsStatus = mutableStateOf(GetConfigStatus.AWAITING)
    val config = mutableListOf<Configs>()

    fun getAllConfigs(){
        viewModelScope.launch {
            config.addAll(repository.getAllConfigs())
            Log.d("ActivityViewModel", "getAllConfigs: $config")
            configsStatus.value = GetConfigStatus.SUCCESS
        }
    }
}