package com.example.cheapfreegames

import android.app.Application
import com.example.cheapfreegames.database.WishlistDatabase

class CheapGamesApplication : Application() {
    val database: WishlistDatabase by lazy { WishlistDatabase.getDatabase(this) }
}