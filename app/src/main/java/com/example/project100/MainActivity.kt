package com.example.project100

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project100.ui.navigation.Screen
import com.example.project100.ui.screens.*
import com.example.project100.ui.theme.Project100Theme
import com.example.project100.viewmodel.MainViewModel
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
                    if (isLocked) {
                        PunishmentOverlay(debt = totalDebt)
                    } else {
                        MainContent()
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                // Simplified bottom bar
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(Screen.Dashboard.route) },
                    icon = { Text("HUD") },
                    label = { Text("DASHBOARD") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Training.route) },
                    icon = { Text("QUEST") },
                    label = { Text("TRAINING") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.History.route) },
                    icon = { Text("LOGS") },
                    label = { Text("HISTORY") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile.route) },
                    icon = { Text("USER") },
                    label = { Text("PROFILE") }
                )
            }
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
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
