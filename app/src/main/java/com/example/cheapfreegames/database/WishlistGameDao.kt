package com.example.cheapfreegames.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistGameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignores a new entity if it's primary key is already in the database
    suspend fun insert(wishlistGame: WishlistGame)

    @Query("DELETE FROM games WHERE gameId = :gameId")
    suspend fun delete(gameId: String)

    @Query("SELECT * FROM games WHERE gameId = :gameId")
    suspend fun getWishlistGameByGameId(gameId: String): WishlistGame

    @Query("SELECT * FROM games")
    suspend fun getWishlistGames(): List<WishlistGame>

}