package com.example.carmaintenancetracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carmaintenancetracker.data.DataRepository
import com.example.carmaintenancetracker.data.MaintenanceModel

@Composable
fun AddMaintenanceScreen(
    carId: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    var type by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var mileage by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 40.dp,
                bottom = 16.dp
            )
    ) {
        Text(
            text = "Добавить обслуживание",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Добавьте информацию об обслуживании",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Тип обслуживания") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Дата") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = cost,
            onValueChange = { cost = it },
            label = { Text("Стоимость") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = mileage,
            onValueChange = { mileage = it },
            label = { Text("Пробег") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val newMaintenance = MaintenanceModel(
                    id = System.currentTimeMillis().toString(),
                    carId = carId,
                    type = type,
                    date = date,
                    cost = cost.toDoubleOrNull() ?: 0.0,
                    mileage = mileage.toIntOrNull() ?: 0,
                    description = description
                )

                DataRepository.maintenanceList.add(newMaintenance)
                onSaveClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Сохранить")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Назад")
        }
    }
}