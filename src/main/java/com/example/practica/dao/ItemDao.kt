package com.example.practica.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practica.entities.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM store_table ORDER BY name ASC")
    fun getAlphabetizedVehicles(): Flow<List<Store>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(store: Store)

    @Query("DELETE FROM store_table WHERE id=:id")
    suspend fun deleteById(id: Int)



}