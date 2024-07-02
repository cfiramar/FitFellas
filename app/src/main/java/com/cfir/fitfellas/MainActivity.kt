package com.cfir.fitfellas

import com.cfir.fitfellas.ui.theme.FitFellasTheme

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFellasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainButton("Combat") { context.startActivity(Intent(context, CombatActivity::class.java)) }
        MainButton("View Fellas") { context.startActivity(Intent(context, ViewFellasActivity::class.java)) }
        MainButton("Personal Stats") { context.startActivity(Intent(context, PersonalStatsActivity::class.java)) }
        MainButton("Drink Water") {
            Toast.makeText(context, "Water drunk! Stay hydrated!", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun MainButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f)
    ) {
        Text(text)
    }
}