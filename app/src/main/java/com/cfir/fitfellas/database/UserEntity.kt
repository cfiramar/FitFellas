package com.cfir.fitfellas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val userId: String = generateUUID(),
    val email: String,
    val waterIntake: Int = 0
)