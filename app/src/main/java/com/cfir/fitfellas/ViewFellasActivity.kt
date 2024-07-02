package com.cfir.fitfellas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Size
import android.graphics.Paint
import android.graphics.Typeface
import kotlinx.coroutines.launch
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
                    ViewFellasScreen(onBackClick = { finish() })
                }
            }
        }
    }
}

@Composable
fun ViewFellasScreen(onBackClick: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val density = LocalDensity.current.density
    val context = LocalContext.current

    val fellas = remember {
        listOf(
            Fitling(screenWidth / 4, screenWidth / 2),
            Fitmore(3 * screenWidth / 4, screenWidth / 2)
        )
    }


    LaunchedEffect(Unit) {
        fellas.forEach { fella ->
            fella.setSize(context)
            launch {
                fella.move(screenWidth, screenHeight)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.view_fellas_background),
            contentDescription = "Fella Field Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Back button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.BottomStart)
                .size(90.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back",
                modifier = Modifier.fillMaxSize()
            )
        }

        // "Your Fellas" text
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.TopCenter)
        ) {
            val paint = Paint().apply {
                textSize = 24.sp.toPx()
                color = android.graphics.Color.WHITE
                typeface = Typeface.DEFAULT_BOLD
                style = Paint.Style.FILL
                textAlign = Paint.Align.CENTER
            }

            val outlinePaint = Paint().apply {
                textSize = 24.sp.toPx()
                color = android.graphics.Color.BLACK
                typeface = Typeface.DEFAULT_BOLD
                style = Paint.Style.STROKE
                strokeWidth = 4f
                textAlign = Paint.Align.CENTER
            }

            val xPos = size.width / 2
            val yPos = size.height / 2 - (paint.descent() + paint.ascent()) / 2

            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText("Your Fellas", xPos, yPos, outlinePaint)
                canvas.nativeCanvas.drawText("Your Fellas", xPos, yPos, paint)
            }
        }

        // Fellas images
        fellas.forEach { fella ->
            Image(
                painter = painterResource(id = fella.drawableResourceId),
                contentDescription = fella.name,
                modifier = Modifier
//                    .size(fella.size.width.dp, fella.size.height.dp)
                    .offset(fella.position.x.dp, fella.position.y.dp)
                    .scale(scaleX = if (fella.isFacingLeft) 1f else -1f, scaleY = 1f)
            )
        }
    }
}
