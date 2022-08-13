package com.example.cheapfreegames.database

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistGameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignores a new entity if it's primary key is already in the database
    suspend fun insert(item: ClipData.Item)

    @Delete
    suspend fun delete(item: ClipData.Item)

    @Query("SELECT * from games")
    fun getWishlistGames(): Flow<List<ClipData.Item>>

}