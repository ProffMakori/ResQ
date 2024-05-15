package com.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.data.FirstAidViewModel
import com.example.project.data.HospitalViewModel
import com.example.project.data.NotificationRepository
import com.example.project.data.NotificationViewModel
import com.example.project.data.SettingsViewModel
import com.example.project.ui.theme.screens.addfirebase.AddFirstAidScreen
import com.example.project.ui.theme.screens.addfirebase.AddHospitalScreen
import com.example.project.ui.theme.screens.admin.AdminScreen
import com.example.project.ui.theme.screens.dashboard.DashboardScreen
import com.example.project.ui.theme.screens.dashboard.MyViewModel
import com.example.project.ui.theme.screens.emergencycontactacts.CovidScreen
import com.example.project.ui.theme.screens.emergencycontactacts.EmergencyScreen
import com.example.project.ui.theme.screens.emergencycontactacts.HospitalsScreen
import com.example.project.ui.theme.screens.firstaid.FirstAidScreen
import com.example.project.ui.theme.screens.home.HomeScreen
import com.example.project.ui.theme.screens.login.LoginScreen
import com.example.project.ui.theme.screens.notifications.NotificationScreen
import com.example.project.ui.theme.screens.products.AddProductsScreen
import com.example.project.ui.theme.screens.products.ViewProductsScreen
import com.example.project.ui.theme.screens.settings.AboutScreen
import com.example.project.ui.theme.screens.settings.SettingsScreen
import com.example.project.ui.theme.screens.signup.SignupScreen
import com.example.project.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController:NavHostController = rememberNavController(), notificationRepository: NotificationRepository,
               viewModel: HospitalViewModel,startDestination:String = SPLASH_URL)
{
    val notificationViewModel = NotificationViewModel(repository = notificationRepository)


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
        composable(ADD_PRODUCTS_URL){
            AddProductsScreen(navController = navController)
        }
        composable(VIEW_PRODUCTS_URL){
            ViewProductsScreen(navController = navController)
        }
        composable(DASHBOARD_URL){
            DashboardScreen(navController = navController, viewModel = MyViewModel())
        }
        composable(SETTINGS_URL){
            SettingsScreen(navController = navController,viewModel = SettingsViewModel())
        }

        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }

        composable(EMERGENCY_URL){
            EmergencyScreen(navController = navController)
        }

        composable(NOTIFICATIONS_URL){
            NotificationScreen(navController = navController, notificationViewModel = notificationViewModel)
        }

        composable(COVID_URL){
            CovidScreen(navController = navController)
        }
        composable(HOSPITAL_URL) {
            HospitalsScreen(navController = navController, viewModel = viewModel)

        }

        composable(ADDHOSPITAL_URL){
            AddHospitalScreen(navController = navController , viewModel = HospitalViewModel())

        }

        composable(ABOUT_URL){
            AboutScreen(navController = navController)

        }
        composable(ADMIN_URL){
            AdminScreen(navController = navController)

        }

        composable(FIRSTAID_URL){
            FirstAidScreen(navController = navController, viewModel = FirstAidViewModel())

        }
        composable(ADDFIRSTAID_URL){
            AddFirstAidScreen(navController = navController, viewModel = viewModel)

        }


    }
}