package com.example.carmaintenancetracker.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomBar(
    currentRoute: String,
    onHomeClick: () -> Unit,
    onCarsClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = onHomeClick,
            icon = { Text("🏠") },
            label = { Text("Главная") }
        )

        NavigationBarItem(
            selected = currentRoute == "cars",
            onClick = onCarsClick,
            icon = { Text("🚗") },
            label = { Text("Авто") }
        )

        NavigationBarItem(
            selected = currentRoute == "statistics",
            onClick = onStatisticsClick,
            icon = { Text("📊") },
            label = { Text("Стат") }
        )

        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = onSettingsClick,
            icon = { Text("⚙") },
            label = { Text("Настр") }
        )
    }
}