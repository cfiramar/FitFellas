package com.cfir.fitfellas

import android.content.Context
import com.cfir.fitfellas.ui.theme.FitFellasTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn

class PersonalStatsActivity : ComponentActivity() {
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        setContent {
            FitFellasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalStatsScreen(viewModel, this)
                }
            }
        }
    }
}

@Composable
fun PersonalStatsScreen(viewModel: AppViewModel, context: Context) {
    val account = GoogleSignIn.getLastSignedInAccount(context)
    val userId = account?.id ?: throw IllegalStateException("User not signed in")
    var waterIntake by remember { mutableStateOf(0) }
    var fellaCount by remember { mutableStateOf(0) }

    LaunchedEffect(userId) {
        userId.let { id ->
            viewModel.getUserID(id)?.let { user ->
                waterIntake = user.waterIntake
            }
            fellaCount = viewModel.getFellaCount(userId)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Personal Stats", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        StatItem("Total Training Time", "10 hours") // You'll need to implement this
        StatItem("Fellas Unlocked", fellaCount.toString())
        StatItem("Water Consumption", "$waterIntake glasses")
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}