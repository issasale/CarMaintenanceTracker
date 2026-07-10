package com.example.carmaintenancetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carmaintenancetracker.data.DataRepository
import com.example.carmaintenancetracker.data.MaintenanceModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMaintenanceScreen(
    carId: String,
    maintenanceId: String? = null,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val existingMaintenance = maintenanceId?.let { id ->
        DataRepository.maintenanceList.find { it.id == id }
    }

    val maintenanceTypes = listOf(
        "Замена масла",
        "Замена воздушного фильтра",
        "Замена салонного фильтра",
        "Замена топливного фильтра",
        "Замена тормозов",
        "Ремонт подвески",
        "Обслуживание кондиционера",
        "Диагностика",
        "Другое"
    )

    var type by remember { mutableStateOf(existingMaintenance?.type ?: maintenanceTypes[0]) }
    var date by remember { mutableStateOf(existingMaintenance?.date ?: "") }
    var cost by remember { mutableStateOf(existingMaintenance?.cost?.toString() ?: "") }
    var mileage by remember { mutableStateOf(existingMaintenance?.mileage?.toString() ?: "") }
    var description by remember { mutableStateOf(existingMaintenance?.description ?: "") }

    var dropdownExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = if (existingMaintenance == null) "Добавить обслуживание" else "Редактировать обслуживание",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = dropdownExpanded,
            onExpandedChange = { dropdownExpanded = !dropdownExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = type,
                onValueChange = {},
                readOnly = true,
                label = { Text("Тип обслуживания") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                singleLine = true
            )
            ExposedDropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false }
            ) {
                maintenanceTypes.forEach { selectedType ->
                    DropdownMenuItem(
                        text = { Text(selectedType) },
                        onClick = {
                            type = selectedType
                            dropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = { Text("Дата обслуживания") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = cost,
            onValueChange = { cost = it },
            label = { Text("Стоимость (₽)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = mileage,
            onValueChange = { mileage = it },
            label = { Text("Пробег (км)") },
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
                if (existingMaintenance == null) {
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
                } else {
                    val index = DataRepository.maintenanceList.indexOf(existingMaintenance)
                    if (index != -1) {
                        DataRepository.maintenanceList[index] = existingMaintenance.copy(
                            type = type,
                            date = date,
                            cost = cost.toDoubleOrNull() ?: 0.0,
                            mileage = mileage.toIntOrNull() ?: 0,
                            description = description
                        )
                    }
                }
                onSaveClick()
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
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

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val selectedDateMillis = datePickerState.selectedDateMillis
                    if (selectedDateMillis != null) {
                        val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                        date = format.format(Date(selectedDateMillis))
                    }
                    showDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Отмена") }
            }
        ) { DatePicker(state = datePickerState) }
    }
}