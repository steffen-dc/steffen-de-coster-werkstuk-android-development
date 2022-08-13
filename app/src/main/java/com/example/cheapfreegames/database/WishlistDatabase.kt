package com.example.cheapfreegames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WishlistGame::class], version = 1, exportSchema = false)
abstract class WishlistDatabase : RoomDatabase() {

    abstract fun wishlistGameDao(): WishlistGameDao

    // singleton
    companion object {
        // The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory. This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads. It means that changes made by one thread to INSTANCE are visible to all other threads immediately.
        @Volatile
        private var INSTANCE: WishlistDatabase? = null

        fun getDatabase(context: Context): WishlistDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WishlistDatabase::class.java, "wishlist_database")
                        .fallbackToDestructiveMigration()
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
