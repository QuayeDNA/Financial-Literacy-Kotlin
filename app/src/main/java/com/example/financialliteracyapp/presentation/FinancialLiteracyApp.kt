package com.example.financialliteracyapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financialliteracyapp.presentation.auth.AuthViewModel
import com.example.financialliteracyapp.presentation.auth.AuthState
import com.example.financialliteracyapp.presentation.auth.LoginScreen
import com.example.financialliteracyapp.presentation.auth.ProfileSetupScreen
import com.example.financialliteracyapp.presentation.auth.RegisterScreen
import com.example.financialliteracyapp.presentation.budget.BudgetScreen
import com.example.financialliteracyapp.presentation.community.CommunityScreen
import com.example.financialliteracyapp.presentation.home.HomeScreen
import com.example.financialliteracyapp.presentation.learn.LearnScreen
import com.example.financialliteracyapp.presentation.onboarding.OnboardingScreen
import com.example.financialliteracyapp.presentation.profile.ProfileScreen

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Learn : Screen("learn", "Learn")
    object Budget : Screen("budget", "Budget")
    object Community : Screen("community", "Community")
    object Profile : Screen("profile", "Profile")
}

val screens = listOf(
    Screen.Home,
    Screen.Learn,
    Screen.Budget,
    Screen.Community,
    Screen.Profile
)

@Composable
fun FinancialLiteracyApp() {
    val authViewModel: AuthViewModel = hiltViewModel()
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    // Determine start destination based on auth state
    val startDestination = when (authState) {
        is AuthState.Unauthenticated -> "login"
        is AuthState.NeedsProfile -> "profile_setup"
        is AuthState.Authenticated -> "main"
        else -> "login"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        // Authentication flow
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("main") },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("profile_setup") },
                onBackToLogin = { navController.popBackStack() }
            )
        }

        composable("profile_setup") {
            ProfileSetupScreen(
                onProfileComplete = { navController.navigate("onboarding") }
            )
        }

        composable("onboarding") {
            OnboardingScreen(
                onComplete = { navController.navigate("main") }
            )
        }

        // Main app with bottom navigation
        composable("main") {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = when (screen) {
                                    Screen.Home -> Icons.Filled.Home
                                    Screen.Learn -> Icons.Filled.School
                                    Screen.Budget -> Icons.Filled.AccountBalanceWallet
                                    Screen.Community -> Icons.Filled.People
                                    Screen.Profile -> Icons.Filled.Person
                                },
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Learn.route) { LearnScreen() }
            composable(Screen.Budget.route) { BudgetScreen() }
            composable(Screen.Community.route) { CommunityScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}