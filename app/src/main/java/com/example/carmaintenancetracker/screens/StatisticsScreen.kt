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
import com.example.carmaintenancetracker.data.DataRepository

@Composable
fun StatisticsScreen(
    onHomeClick: () -> Unit,
    onCarsClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val totalExpenses = DataRepository.maintenanceList.sumOf { it.cost }
    val maintenanceCount = DataRepository.maintenanceList.size
    val carsCount = DataRepository.carsList.size

    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = "statistics",
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
                text = "Статистика",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Количество автомобилей: $carsCount")
            Text("Количество обслуживаний: $maintenanceCount")
            Text("Общие расходы: ${totalExpenses.toInt()} ₽")
        }
    }
}