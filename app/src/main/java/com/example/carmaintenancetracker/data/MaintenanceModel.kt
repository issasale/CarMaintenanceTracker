package com.example.carmaintenancetracker.data

data class MaintenanceModel(
    val id: String,
    val carId: String,
    val type: String,
    val date: String,
    val cost: Double,
    val mileage: Int,
    val description: String
)