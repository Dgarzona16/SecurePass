package com.dgarzona16.securepass.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgarzona16.securepass.data.repository.ConfigsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val configsRepository: ConfigsRepository
): ViewModel() {

    fun insertConfig(key: String, value: String) {
        viewModelScope.launch {
            configsRepository.insertConfig(key, value)
        }
    }
}