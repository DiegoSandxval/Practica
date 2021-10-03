package com.example.practica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practica.dao.ItemDao
import com.example.practica.entities.Store

@Database(entities = [Store::class],version = 1,exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun ItemDao():ItemDao

    companion object{
        @Volatile
        private  var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context):ItemRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}