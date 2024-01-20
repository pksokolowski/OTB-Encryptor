package com.github.pksokolowski.account.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.github.pksokolowski.account.register.widget.PasswordStrengthIndicator


@Composable
internal fun RegistrationScreen(
    uiState: UiState,
    onPassword1Changed: (String) -> Unit,
    onPassword2Changed: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    Column {
        Text("Register!")

        Row {
            TextField(
                value = uiState.password1.password,
                onValueChange = { onPassword1Changed(it) },
                modifier = Modifier.weight(4f),
                label = { Text("Secret login") },
                singleLine = true,
                placeholder = { Text("Secret login") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            PasswordStrengthIndicator(
                strength = uiState.password1.strengthAssessment,
                modifier = Modifier.weight(1f)
            )
        }

        Row {
            TextField(
                value = uiState.password2.password,
                onValueChange = { onPassword2Changed(it) },
                modifier = Modifier.weight(4f),
                label = { Text("Password") },
                singleLine = true,
                placeholder = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            PasswordStrengthIndicator(
                strength = uiState.password2.strengthAssessment,
                modifier = Modifier.weight(1f)
            )
        }

        Button(onClick = { onConfirm() }) {
            Text(text = "Next")
        }
    }

}