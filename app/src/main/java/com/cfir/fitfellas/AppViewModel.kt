package com.cfir.fitfellas;

import com.cfir.fitfellas.database.AppDatabase
import com.cfir.fitfellas.database.FellaEntity
import com.cfir.fitfellas.database.UserEntity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(private val database: AppDatabase) : ViewModel() {
    private val userDao = database.userDao()
    private val fellaDao = database.fellaDao()

    fun saveUser(userId: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = UserEntity(email = email)
            userDao.insertUser(user)
        }
    }

    fun updateWaterIntake(userId: String, intake: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.getUserId(userId)
            user?.let {
                val updatedUser = it.copy(waterIntake = it.waterIntake + intake)
                userDao.updateUser(updatedUser)
            }
        }
    }

    fun saveFella(fella: Fella) {
        viewModelScope.launch(Dispatchers.IO) {
            val fellaEntity = FellaEntity(
                userId = fella.userId,
                name = fella.name,
                nickname = fella.nickname,
                level = fella.level,
                experience = fella.experience,
            )
            fellaDao.insertFella(fellaEntity)
        }
    }

    suspend fun loadFellas(userId: String): List<Fella> {
        return fellaDao.getFellasForUser(userId).map { entity ->
            when (entity.name) {
                "Fitling" -> Fitling(entity.id, entity.userId, entity.nickname, entity.level, entity.experience)
                "Fitmore" -> Fitmore(entity.id, entity.userId, entity.nickname, entity.level, entity.experience)
                else -> throw IllegalArgumentException("Unknown Fella: ${entity.name}")
            }
        }
    }

    suspend fun getUserID(userId: String): UserEntity? {
        return userDao.getUserId(userId)
    }

    suspend fun getFellaCount(userId: String): Int {
        return fellaDao.getFellasForUser(userId).size
    }
}
