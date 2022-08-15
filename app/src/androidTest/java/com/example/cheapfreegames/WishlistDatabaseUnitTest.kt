package com.example.cheapfreegames

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cheapfreegames.database.WishlistDatabase
import com.example.cheapfreegames.database.WishlistGame
import com.example.cheapfreegames.database.WishlistGameDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class WishlistDatabaseUnitTest {
    private lateinit var _wishlistGameDao: WishlistGameDao
    private lateinit var _database: WishlistDatabase

    @Before
    fun createDatabase(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        _database = Room.inMemoryDatabaseBuilder(context, WishlistDatabase::class.java).build()
        _wishlistGameDao = _database.wishlistGameDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        _database.close()
    }

    @Test
    @Throws(Exception::class)
    fun wishListGame_InsertGetDelete() = runTest {
        val wishlistGame = WishlistGame(gameId = "555")

        _wishlistGameDao.insert(wishlistGame)
        val databaseWishlistGame = _wishlistGameDao.getWishlistGameByGameId("555")
        assertTrue(wishlistGame.gameId == databaseWishlistGame.gameId)

        _wishlistGameDao.delete("555")
        val deletedWishlistGame = _wishlistGameDao.getWishlistGameByGameId("555")
        assertNull(deletedWishlistGame)
    }
}