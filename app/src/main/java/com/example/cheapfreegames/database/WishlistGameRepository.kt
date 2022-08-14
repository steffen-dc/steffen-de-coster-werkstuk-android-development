package com.example.cheapfreegames.database

import kotlinx.coroutines.flow.Flow

class WishlistGameRepository(private val wishlistGameDao: WishlistGameDao) {

    suspend fun insertWishlistGame(wishlistGame: WishlistGame) {
        wishlistGameDao.insert(wishlistGame)
    }

    suspend fun deleteWishlistGame(gameId: String) {
        wishlistGameDao.delete(gameId)
    }

    suspend fun getWishlistGameByGameId(gameId: String) : WishlistGame {
        return wishlistGameDao.getWishlistGameByGameId(gameId)
    }

    suspend fun getWishlistGames() : List<WishlistGame> {
        return wishlistGameDao.getWishlistGames()
    }
}