package com.github.pksokolowski.account.register.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.pksokolowski.account.R
import com.github.pksokolowski.account.register.domain.PasswordStrength

@Composable
internal fun PasswordStrengthIndicator(
    strength: PasswordStrength?,
    modifier: Modifier = Modifier,
) {
    val description by remember(strength) {
        mutableStateOf(
            when (strength) {
                PasswordStrength.WEAK -> R.string.pass_strength_weak
                PasswordStrength.CASUAL -> R.string.pass_strength_casual
                PasswordStrength.DECENT -> R.string.pass_strength_decent
                PasswordStrength.ENTERPRISE -> R.string.pass_strength_enterprise
                PasswordStrength.TOP_SECRET -> R.string.pass_strength_top_secret
                PasswordStrength.MAXED_OUT -> R.string.pass_strength_maxed_out
                null -> null
            }
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        AnimatedVisibility(visible = description != null) {
            Text(stringResource(id = description!!))
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    MaterialTheme {
        PasswordStrengthIndicator(
            strength = PasswordStrength.WEAK
        )
    }
}