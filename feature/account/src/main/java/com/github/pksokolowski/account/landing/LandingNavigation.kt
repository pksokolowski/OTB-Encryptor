package com.github.pksokolowski.account.landing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

internal const val LandingRoute = "account/landing"

fun NavController.navigateToLanding(navOptions: NavOptions? = null) {
    this.navigate(LandingRoute, navOptions)
}

internal fun NavGraphBuilder.landingScreen(
    onNeedToRegister: () -> Unit,
) {
    composable(LandingRoute) {
        val viewModel: LandingViewModel = hiltViewModel()

        HandleResult(
            onNeedToRegister = onNeedToRegister,
            viewModel = viewModel,
        )

        LandingScreen(

        )
    }
}

@Composable
private fun HandleResult(
    onNeedToRegister: () -> Unit,
    viewModel: LandingViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.destination.collect { destination ->
            when (destination) {
                Destination.Registration -> onNeedToRegister()
                Destination.Login -> TODO()
                null -> Unit
            }
        }
    }
}