package com.dgarzona16.securepass.ui.component.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dgarzona16.securepass.R

@Composable
fun TextFieldSP(
    label: String? = null,
    value: MutableState<String>,
    icon: @Composable (() -> Unit)? = null,
    isError: MutableState<Boolean> = mutableStateOf(false),
    supportText: MutableState<String> = mutableStateOf(""),
    type: KeyboardType = KeyboardType.Text,
    onChangeValue: (String) -> Unit = {},
    float: Float = 1f,
    padding: Dp = 16.dp,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    isEnabled: MutableState<Boolean> = mutableStateOf(true)
) {
    val showContent = remember { mutableStateOf(true) }
    val visualTransformation =
        if (type == KeyboardType.Password && showContent.value)
            PasswordVisualTransformation()
        else
            VisualTransformation.None

    val tIcon = @Composable {
        Icon(
            painter = painterResource(id = if (showContent.value) R.drawable.visibility else R.drawable.visibility_off),
            contentDescription = "Password Visibility",
            modifier = Modifier.clickable {
                showContent.value = !showContent.value
            }
        )
    }

    val tLabel = @Composable {
        Text(
            text = label ?: "",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )
    }

    OutlinedTextField(
        value = value.value,
        onValueChange = {
            onChangeValue(it)
            value.value = it
        },
        modifier = Modifier
            .fillMaxWidth(float)
            .padding(horizontal = padding),
        label = if (label != null) tLabel else null,
        isError = isError.value,
        visualTransformation = visualTransformation,
        supportingText = {
            if (isError.value) {
                Text(
                    text = supportText.value,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )
            }
        },
        keyboardOptions = when (type) {
            KeyboardType.Text -> KeyboardType.Text.keyboardType
            KeyboardType.Number -> KeyboardType.Number.keyboardType
            KeyboardType.Email -> KeyboardType.Email.keyboardType
            KeyboardType.Password -> KeyboardType.Password.keyboardType
        },
        leadingIcon = icon,
        trailingIcon = if (type == KeyboardType.Password) tIcon else null,
        enabled = isEnabled.value,
        textStyle = textStyle,
        singleLine = true
    )
}

sealed class KeyboardType() {
    object Text : KeyboardType() {
        val keyboardType = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Text)
    }

    object Number : KeyboardType() {
        val keyboardType = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
    }

    object Email : KeyboardType() {
        val keyboardType = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Email)
    }
    object Password : KeyboardType() {
        val keyboardType = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Password)
    }
}