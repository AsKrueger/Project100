package com.example.project100

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.project100.ui.navigation.Screen
import com.example.project100.ui.screens.*
import com.example.project100.ui.theme.Project100Theme
import com.example.project100.viewmodel.MainViewModel
import com.example.project100.ui.components.SystemNavigationBar
import com.example.project100.ui.components.SystemBackgroundEffects
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project100Theme {
                val isLocked by mainViewModel.isLocked.collectAsState()
                val totalDebt by mainViewModel.totalDebt.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        SystemBackgroundEffects()
                        
                        // El contenido principal siempre está debajo
                        MainContent()

                        // El panel de penalización aparece como un "floating overlay"
                        AnimatedVisibility(
                            visible = isLocked,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            PunishmentOverlay(debt = totalDebt)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        bottomBar = {
            SystemNavigationBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Training.route) { TrainingScreen() }
            composable(Screen.History.route) { HistoryScreen() }
            composable(Screen.Profile.route) { 
                ProfileScreen(onNavigateToMetrics = {
                    navController.navigate(Screen.BodyMetrics.route)
                }) 
            }
            composable(Screen.BodyMetrics.route) { 
                BodyMetricsScreen(onBack = {
                    navController.popBackStack()
                }) 
            }
        }
    }
}
