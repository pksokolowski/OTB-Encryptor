package com.github.pksokolowski.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.github.pksokolowski.account.landing.LandingRoute
import com.github.pksokolowski.account.landing.landingScreen

val AccountGraphRoutePattern = "account"

fun NavController.navigateToContactsGraph(navOptions: NavOptions? = null) {
    this.navigate(AccountGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.accountGraph(navController: NavController) {
    navigation(
        startDestination = LandingRoute,
        route = AccountGraphRoutePattern
    ) {
        landingScreen()
    }
}