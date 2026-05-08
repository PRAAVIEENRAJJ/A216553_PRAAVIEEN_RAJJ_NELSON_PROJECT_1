package com.example.a216553_praavieen_rajj_nelson_project_1.Navigation



import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a216553_praavieen_rajj_nelson_project_1.Screens.AddEnvelopeScreen
import com.example.a216553_praavieen_rajj_nelson_project_1.Screens.HomeScreen
import com.example.a216553_praavieen_rajj_nelson_project_1.Screens.LoginScreen
import com.example.a216553_praavieen_rajj_nelson_project_1.Screens.ProfileScreen
import com.example.a216553_praavieen_rajj_nelson_project_1.Screens.RegisterScreen
import com.example.a216553_praavieen_rajj_nelson_project_1.ViewModel.UserViewModel

// Enum for routes
enum class Screen { LOGIN, REGISTER, HOME, PROFILE, ADD_ENVELOPE }

@Composable
fun AppNavigator(userViewModel: UserViewModel = viewModel()) {
    val navController = rememberNavController()

    // Requirements: Minimum 5 screens implemented via Navigation Compose [cite: 10, 30]
    NavHost(navController = navController, startDestination = Screen.LOGIN.name) {

        composable(Screen.LOGIN.name) {
            LoginScreen(
                onLoginSuccess = { username ->
                    userViewModel.updateFromLogin(username)
                    navController.navigate(Screen.HOME.name)
                },
                onGoToRegister = { navController.navigate(Screen.REGISTER.name) }
            )
        }

        composable(Screen.REGISTER.name) {
            RegisterScreen(
                onRegisterSuccess = { f, u, e, p ->
                    userViewModel.updateFromRegister(f, u, e, p)
                    navController.navigate(Screen.HOME.name)
                },
                onGoToLogin = { navController.popBackStack() }
            )
        }

        composable(Screen.HOME.name) {
            val profile by userViewModel.uiState.collectAsState()
            val envelopes by userViewModel.envelopes.collectAsState()
            HomeScreen(
                profile = profile,
                envelopeList = envelopes,
                onNavigateToAdd = { navController.navigate(Screen.ADD_ENVELOPE.name) },
                onViewProfile = { navController.navigate(Screen.PROFILE.name) },
                onLogout = {
                    userViewModel.clear()
                    navController.navigate(Screen.LOGIN.name) { popUpTo(0) }
                }
            )
        }

        composable(Screen.ADD_ENVELOPE.name) {
            AddEnvelopeScreen(
                onEnvelopeAdded = { t, b ->
                    userViewModel.addEnvelope(t, b)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.PROFILE.name) {
            val profile by userViewModel.uiState.collectAsState()
            ProfileScreen(profile = profile, onBack = { navController.popBackStack() })
        }
    }
}