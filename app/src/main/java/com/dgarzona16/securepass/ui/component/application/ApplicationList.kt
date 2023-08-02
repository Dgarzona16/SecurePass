package com.dgarzona16.securepass.ui.component.application

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import com.dgarzona16.securepass.data.entities.Applications

@Composable
fun ApplicationList(
    applications: List<Applications>,
    onEditClick: (Applications) -> Unit = {},
    onDeleteClick: (Applications) -> Unit = {}
) {
    LazyColumn(){
        items(applications.size) { index ->
            ApplicationItem(
                application = applications[index],
                onEditClick = { onEditClick(applications[index]) },
                onDeleteClick = { onDeleteClick(applications[index]) }
            )
        }
    }
}