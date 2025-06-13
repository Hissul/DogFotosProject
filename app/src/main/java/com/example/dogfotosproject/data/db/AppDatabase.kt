package com.example.dogfotosproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogfotosproject.data.db.dao.DogDao
import com.example.dogfotosproject.data.db.dao.UserDao
import com.example.dogfotosproject.data.db.entity.FavoriteDogEntity
import com.example.dogfotosproject.data.db.entity.UserEntity

@Database(entities = [FavoriteDogEntity::class, UserEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "dogs_database.db"
            ).fallbackToDestructiveMigration().build()
    }
}