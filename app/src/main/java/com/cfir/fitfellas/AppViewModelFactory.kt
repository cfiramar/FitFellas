package com.cfir.fitfellas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cfir.fitfellas.database.AppDatabase

class AppViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}