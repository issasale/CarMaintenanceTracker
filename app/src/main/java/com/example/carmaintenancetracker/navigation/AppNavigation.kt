package com.example.carmaintenancetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carmaintenancetracker.screens.AddCarScreen
import com.example.carmaintenancetracker.screens.CarDetailsScreen
import com.example.carmaintenancetracker.screens.CarsScreen
import com.example.carmaintenancetracker.screens.HomeScreen
import com.example.carmaintenancetracker.screens.SettingsScreen
import com.example.carmaintenancetracker.screens.StatisticsScreen
import com.example.carmaintenancetracker.screens.AddMaintenanceScreen

object Routes {
    const val HOME = "home"
    const val CARS = "cars"
    const val ADD_CAR = "add_car"
    const val EDIT_CAR = "edit_car"
    const val CAR_DETAILS = "car_details"
    const val ADD_MAINTENANCE = "add_maintenance"
    const val EDIT_MAINTENANCE = "edit_maintenance"
    const val STATISTICS = "statistics"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onAddCarClick = { navController.navigate(Routes.ADD_CAR) },
                onHomeClick = { navController.navigate(Routes.HOME) },
                onCarsClick = { navController.navigate(Routes.CARS) },
                onStatisticsClick = { navController.navigate(Routes.STATISTICS) },
                onSettingsClick = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.CARS) {
            CarsScreen(
                onAddCarClick = { navController.navigate(Routes.ADD_CAR) },
                onCarClick = { carId -> navController.navigate("${Routes.CAR_DETAILS}/$carId") },
                onHomeClick = { navController.navigate(Routes.HOME) },
                onCarsClick = { navController.navigate(Routes.CARS) },
                onStatisticsClick = { navController.navigate(Routes.STATISTICS) },
                onSettingsClick = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.ADD_CAR) {
            AddCarScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.navigate(Routes.CARS) }
            )
        }

        composable("${Routes.EDIT_CAR}/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")
            AddCarScreen(
                carId = carId,
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.navigate(Routes.CARS) }
            )
        }

        composable("${Routes.CAR_DETAILS}/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            CarDetailsScreen(
                carId = carId,
                onBackClick = { navController.popBackStack() },
                onEditClick = { navController.navigate("${Routes.EDIT_CAR}/$carId") },
                onDeleteClick = { navController.navigate(Routes.CARS) },
                onAddMaintenanceClick = { navController.navigate("${Routes.ADD_MAINTENANCE}/$carId") },
                onEditMaintenanceClick = { maintenanceId ->
                    navController.navigate("${Routes.EDIT_MAINTENANCE}/$carId/$maintenanceId")
                }
            )
        }

        composable(Routes.STATISTICS) {
            StatisticsScreen(
                onHomeClick = { navController.navigate(Routes.HOME) },
                onCarsClick = { navController.navigate(Routes.CARS) },
                onStatisticsClick = { navController.navigate(Routes.STATISTICS) },
                onSettingsClick = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onHomeClick = { navController.navigate(Routes.HOME) },
                onCarsClick = { navController.navigate(Routes.CARS) },
                onStatisticsClick = { navController.navigate(Routes.STATISTICS) },
                onSettingsClick = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable("${Routes.ADD_MAINTENANCE}/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            AddMaintenanceScreen(
                carId = carId,
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.popBackStack() }
            )
        }

        composable("${Routes.EDIT_MAINTENANCE}/{carId}/{maintenanceId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            val maintenanceId = backStackEntry.arguments?.getString("maintenanceId")
            AddMaintenanceScreen(
                carId = carId,
                maintenanceId = maintenanceId,
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.popBackStack() }
            )
        }
    }
}