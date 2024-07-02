package com.cfir.fitfellas

import com.cfir.fitfellas.ui.theme.FitFellasTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class PersonalStatsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFellasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalStatsScreen()
                }
            }
        }
    }
}

@Composable
fun PersonalStatsScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Personal Stats", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        StatItem("Total Training Time", "10 hours")
        StatItem("Fellas Unlocked", "5")
        StatItem("Average Water Consumption", "8 glasses/day")
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
    Spacer(modifier = Modifier.height(8.dp))
}