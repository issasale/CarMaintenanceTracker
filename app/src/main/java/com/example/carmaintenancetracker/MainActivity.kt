package com.example.carmaintenancetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.carmaintenancetracker.navigation.AppNavigation
import com.example.carmaintenancetracker.ui.theme.CarMaintenanceTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarMaintenanceTrackerTheme {
                AppNavigation()
            }
        }
    }
}