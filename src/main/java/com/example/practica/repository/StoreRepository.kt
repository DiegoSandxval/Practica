package com.example.practica.repository

import android.content.Context
import com.example.practica.dao.ItemDao
import com.example.practica.database.ItemRoomDatabase
import com.example.practica.entities.Store
import kotlinx.coroutines.flow.Flow

class StoreRepository (private val ItemDao: ItemDao){

    companion object{
        private var INSTANCE : StoreRepository? = null
        fun getRepository(context: Context) :StoreRepository{
            return INSTANCE ?: synchronized(this){
                val database = ItemRoomDatabase.getDatabase(context)
                val instance = StoreRepository(database.ItemDao())
                INSTANCE = instance
                instance
            }
        }
    }
    val allItems: Flow<List<Store>> = ItemDao.getAlphabetizedVehicles()
    
    suspend fun insert(store: Store){
        ItemDao.insert(store)
    }
    suspend fun deleteById(id:Int){
        ItemDao.deleteById(id)
    }

}