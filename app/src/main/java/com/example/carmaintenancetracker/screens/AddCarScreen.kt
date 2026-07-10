package com.example.carmaintenancetracker.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carmaintenancetracker.data.CarModel
import com.example.carmaintenancetracker.data.DataRepository

@Composable
fun AddCarScreen(
    carId: String? = null,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val existingCar = carId?.let { id ->
        DataRepository.carsList.find { car -> car.id == id }
    }

    var brand by remember { mutableStateOf(existingCar?.brand ?: "") }
    var model by remember { mutableStateOf(existingCar?.model ?: "") }
    var year by remember { mutableStateOf(existingCar?.year?.toString() ?: "") }
    var color by remember { mutableStateOf(existingCar?.color ?: "") }
    var plateNumber by remember { mutableStateOf(existingCar?.plateNumber ?: "") }
    var mileage by remember { mutableStateOf(existingCar?.mileage?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 32.dp,
                bottom = 16.dp
            )
    ) {
        Text(
            text = if (existingCar == null) "Добавить автомобиль" else "Редактировать автомобиль",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (existingCar == null)
                "Добавьте информацию о вашем автомобиле"
            else
                "Измените информацию об автомобиле",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = brand,
            onValueChange = { brand = it },
            label = { Text("Марка") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Модель") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Год выпуска") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = color,
            onValueChange = { color = it },
            label = { Text("Цвет") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = plateNumber,
            onValueChange = { plateNumber = it },
            label = { Text("Госномер") },
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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (existingCar == null) {
                    DataRepository.carsList.add(
                        CarModel(
                            id = System.currentTimeMillis().toString(),
                            brand = brand,
                            model = model,
                            year = year.toIntOrNull() ?: 0,
                            color = color,
                            plateNumber = plateNumber,
                            mileage = mileage.toIntOrNull() ?: 0
                        )
                    )
                } else {
                    val index = DataRepository.carsList.indexOf(existingCar)

                    if (index != -1) {
                        DataRepository.carsList[index] = existingCar.copy(
                            brand = brand,
                            model = model,
                            year = year.toIntOrNull() ?: 0,
                            color = color,
                            plateNumber = plateNumber,
                            mileage = mileage.toIntOrNull() ?: 0
                        )
                    }
                }

                onSaveClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Сохранить")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Назад")
        }
    }
}