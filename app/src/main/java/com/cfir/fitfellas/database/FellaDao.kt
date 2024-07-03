package com.cfir.fitfellas.database;

import androidx.room.*

@Dao
interface FellaDao {
    @Query("SELECT * FROM fellas WHERE userId = :userId")
    fun getFellasForUser(userId: String): List<FellaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFella(fella: FellaEntity): Long

    @Update
    fun updateFella(fella: FellaEntity)

    @Delete
    fun deleteFella(fella: FellaEntity)
}