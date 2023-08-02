package com.dgarzona16.securepass.ui.component.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun DividerSP(
    text: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = text
        )
        Divider(
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview
@Composable
fun DividerSPPreview() {
    DividerSP(text = "Hello")
}