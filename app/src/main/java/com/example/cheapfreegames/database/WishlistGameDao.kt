package com.example.cheapfreegames.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistGameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignores a new entity if it's primary key is already in the database
    suspend fun insert(wishlistGame: WishlistGame)

    @Delete
    suspend fun delete(wishlistGame: WishlistGame)

    @Query("SELECT * FROM games WHERE gameId = :gameId")
    fun getWishlistGameByGameId(gameId: String): WishlistGame

    @Query("SELECT * FROM games")
    fun getWishlistGames(): Flow<List<WishlistGame>>

}