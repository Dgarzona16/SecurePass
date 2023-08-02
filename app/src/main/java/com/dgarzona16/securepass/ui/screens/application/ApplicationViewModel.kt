package com.dgarzona16.securepass.ui.screens.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgarzona16.securepass.data.entities.Applications
import com.dgarzona16.securepass.data.repository.ApplicationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val repository: ApplicationsRepository
) : ViewModel() {
    private var apps: MutableList<Applications> = mutableListOf()
    val applications: List<Applications> = apps
    init {
        viewModelScope.launch {
            apps.addAll(repository.getAllApplications())
        }
    }

    fun addApplication(value: String) {
        viewModelScope.launch {
            repository.insertApplication(value)
            apps = repository.getAllApplications().toMutableList()
        }
    }
}