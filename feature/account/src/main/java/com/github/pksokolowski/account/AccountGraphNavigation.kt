package com.github.pksokolowski.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.github.pksokolowski.account.landing.LandingRoute
import com.github.pksokolowski.account.landing.landingScreen
import com.github.pksokolowski.account.register.navigateToRegistration
import com.github.pksokolowski.account.register.registrationScreen

val AccountGraphRoutePattern = "account"

fun NavController.navigateToContactsGraph(navOptions: NavOptions? = null) {
    this.navigate(AccountGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.accountGraph(navController: NavController) {
    navigation(
        startDestination = LandingRoute,
        route = AccountGraphRoutePattern
    ) {
        landingScreen(
            onNeedToRegister = { navController.navigateToRegistration() }
        )
        registrationScreen()
    }
}