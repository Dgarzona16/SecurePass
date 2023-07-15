package com.dgarzona16.securepass.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dgarzona16.securepass.R
import com.dgarzona16.securepass.data.entities.Accounts
import java.util.UUID

@Composable
fun AccountItem(
    account: Accounts,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val passwordVisible = remember { mutableStateOf(false)}
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = account.tag,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = stringResource(R.string.username),
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = account.username,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = if (passwordVisible.value) account.password else "********",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Row() {
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    painter = if (passwordVisible.value)
                        painterResource(id = R.drawable.visibility)
                    else
                        painterResource(id = R.drawable.visibility_off),
                    contentDescription = if (passwordVisible.value)
                        "Hide Password"
                    else
                        "Show Password"
                )
            }

            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
            IconButton(onClick = {
                clipboardManager.setText(AnnotatedString(account.password))
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }) {
                Icon(painter = painterResource(id = R.drawable.content_copy), contentDescription = "Copy")
            }
        }
    }
}

@Composable
@Preview
fun AccountItemPreview() {
    val account = Accounts(
        UUID.randomUUID(),
        "Cuenta de trabajo",
        "dgarzona@sannicolas.com.sv",
        "123456789",
        UUID.randomUUID()
    )
    AccountItem(account)
}