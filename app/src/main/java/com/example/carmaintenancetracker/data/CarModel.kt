package com.example.carmaintenancetracker.data

data class CarModel(
    val id: String,
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val plateNumber: String,
    val mileage: Int
)