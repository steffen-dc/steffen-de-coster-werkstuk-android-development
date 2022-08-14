package com.example.cheapfreegames.ui.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cheapfreegames.database.WishlistGame
import com.example.cheapfreegames.database.WishlistGameDao
import com.example.cheapfreegames.database.WishlistGameRepository
import com.example.cheapfreegames.network.model.ApiStatus
import com.example.cheapfreegames.network.model.ListOfGamesResult
import kotlinx.coroutines.flow.Flow

class WishlistViewModel(private val wishlistGameDao: WishlistGameDao) : ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _listOfGamesResults = MutableLiveData<List<ListOfGamesResult>>()
    val listOfGamesResults: LiveData<List<ListOfGamesResult>> = _listOfGamesResults

    private val _repository: WishlistGameRepository = WishlistGameRepository(wishlistGameDao)

    private val _wishlistGames : Flow<List<WishlistGame>> = _repository.getWishlistGames()

    init{
    }

    private fun fetchWishlistedGames(){

    }
}

// boiler plate code - can reuse
class WishlistViewModelFactory(private val wishlistGameDao: WishlistGameDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WishlistViewModel(wishlistGameDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}