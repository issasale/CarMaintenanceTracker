package com.example.carmaintenancetracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carmaintenancetracker.components.BottomBar

@Composable
fun SettingsScreen(
    onHomeClick: () -> Unit,
    onCarsClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = "settings",
                onHomeClick = onHomeClick,
                onCarsClick = onCarsClick,
                onStatisticsClick = onStatisticsClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Настройки",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Язык: Русский")
            Text("Валюта: Рубль ₽")
            Text("Версия приложения: 1.0")
        }
    }
}
