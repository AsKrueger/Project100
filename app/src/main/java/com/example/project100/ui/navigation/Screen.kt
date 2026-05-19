package com.example.project100.ui.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Training : Screen("training")
    object Punishment : Screen("punishment")
    object History : Screen("history")
    object Profile : Screen("profile")
}
