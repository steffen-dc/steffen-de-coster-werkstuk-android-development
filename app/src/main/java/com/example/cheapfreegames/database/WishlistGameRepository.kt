package com.example.cheapfreegames.database

import kotlinx.coroutines.flow.Flow

class WishlistGameRepository(private val wishlistGameDao: WishlistGameDao) {

    suspend fun insertWishlistGame(wishlistGame: WishlistGame) {
        wishlistGameDao.insert(wishlistGame)
    }

    suspend fun deleteWishlistGame(wishlistGame: WishlistGame) {
        wishlistGameDao.delete(wishlistGame)
    }

    fun getWishlistGameByGameId(gameId: String) : WishlistGame {
        return wishlistGameDao.getWishlistGameByGameId(gameId)
    }

    fun getWishlistGames() : Flow<List<WishlistGame>> {
        return wishlistGameDao.getWishlistGames()
    }
}