package com.cfir.fitfellas

import com.cfir.fitfellas.ui.theme.FitFellasTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ViewFellasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFellasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewFellasScreen()
                }
            }
        }
    }
}

@Composable
fun ViewFellasScreen() {
    Column {
        Text("Your Fellas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        PixelArtField()
    }
}

@Composable
fun PixelArtField() {
    Canvas(modifier = Modifier.size(300.dp)) {
        // This is a very basic example. You'd need to implement
        // your actual pixel art and Fella rendering logic here.
        drawRect(Color.Green, Offset(0f, 0f), size)
        // Add your Fella rendering logic here
    }
}