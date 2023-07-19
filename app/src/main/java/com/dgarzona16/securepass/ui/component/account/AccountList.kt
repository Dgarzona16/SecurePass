package com.dgarzona16.securepass.ui.component.account

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.dgarzona16.securepass.data.entities.Accounts

@Composable
fun AccountList(
    accounts: List<Accounts>,
    onEditClick: (Accounts) -> Unit = {},
    onDeleteClick: (Accounts) -> Unit = {}
) {
    LazyColumn(){
        items(accounts.size) { index ->
            AccountItem(
                account = accounts[index],
                onEditClick = { onEditClick(accounts[index]) },
                onDeleteClick = { onDeleteClick(accounts[index]) }
            )
        }
    }
}