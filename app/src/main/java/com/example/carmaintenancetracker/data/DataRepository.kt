package com.example.carmaintenancetracker.data

import androidx.compose.runtime.mutableStateListOf

object DataRepository {

    val carsList = mutableStateListOf(
        CarModel(
            id = "1",
            brand = "Toyota",
            model = "Corolla",
            year = 2008,
            color = "Серебристый",
            plateNumber = "A123BC",
            mileage = 153000
        ),
        CarModel(
            id = "2",
            brand = "Lada",
            model = "Kalina",
            year = 2011,
            color = "Белый",
            plateNumber = "B456CC",
            mileage = 188000
        )
    )

    val maintenanceList = mutableStateListOf(
        MaintenanceModel(
            id = "1",
            carId = "1",
            type = "Замена масла",
            date = "03.07.2026",
            cost = 3000.0,
            mileage = 153000,
            description = "Масло и фильтр заменены"
        ),
        MaintenanceModel(
            id = "2",
            carId = "1",
            type = "Замена фильтра",
            date = "12.05.2026",
            cost = 1500.0,
            mileage = 150000,
            description = "Заменен воздушный фильтр"
        )
    )
}