package com.cfir.fitfellas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cfir.fitfellas.ui.theme.FitFellasTheme

class ViewFellasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFellasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewFellasScreen {
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun ViewFellasScreen(onBackClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.view_fellas_background),
            contentDescription = "Fella Field Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Fitling image
        Image(
            painter = painterResource(id = R.drawable.fitling),
            contentDescription = "Fitling",
            modifier = Modifier.size(100.dp).align(Alignment.Center)
        )

        // Outlined "Your Fellas" text
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            Text(
                "Your Fellas",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 32.sp),
                color = Color.Black,
                modifier = Modifier.offset(x = 1.dp, y = 1.dp)
            )
            Text(
                "Your Fellas",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 32.sp),
                color = Color.White
            )
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text("Back")
        }
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
