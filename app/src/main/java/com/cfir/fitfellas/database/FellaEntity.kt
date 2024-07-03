package com.cfir.fitfellas.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "fellas",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FellaEntity(
    @PrimaryKey val id: String = generateUUID(),
    val userId: String,
    val name: String,
    val nickname: String,
    val level: Int,
    val experience: Int,
)