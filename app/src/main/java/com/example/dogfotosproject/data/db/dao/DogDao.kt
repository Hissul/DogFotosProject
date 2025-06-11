package com.example.dogfotosproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogfotosproject.data.db.entity.FavoriteDogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(dog: FavoriteDogEntity)

    @Query("SELECT * FROM favorite_dogs WHERE userId = :id")
    fun getAllFavorites(id: Int): Flow<List<FavoriteDogEntity>>
}