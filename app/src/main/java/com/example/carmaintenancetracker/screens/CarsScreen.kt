package com.example.carmaintenancetracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carmaintenancetracker.components.BottomBar
import com.example.carmaintenancetracker.data.DataRepository
import androidx.compose.foundation.clickable

@Composable
fun CarsScreen(
    onAddCarClick: () -> Unit,
    onCarClick: (String) -> Unit,
    onHomeClick: () -> Unit,
    onCarsClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = "cars",
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
                text = "Мои автомобили",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Выберите автомобиль для просмотра деталей",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAddCarClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("+ Добавить автомобиль")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(DataRepository.carsList) { car ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clickable {
                                onCarClick(car.id)
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "${car.brand} ${car.model}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text("Год выпуска: ${car.year}")
                            Text("Цвет: ${car.color}")
                            Text("Госномер: ${car.plateNumber}")
                            Text("Пробег: ${car.mileage} км")
                        }
                    }
                }
            }
        }
    }
}