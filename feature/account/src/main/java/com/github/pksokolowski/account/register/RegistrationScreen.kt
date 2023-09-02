package com.github.pksokolowski.account.register

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType


@Composable
internal fun RegistrationScreen(
    uiState: UiState,
    onPassword1Changed: (String) -> Unit,
    onPassword2Changed: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    Text("Register!")

    TextField(
        value = uiState.password1,
        onValueChange = { onPassword1Changed(it) },
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}