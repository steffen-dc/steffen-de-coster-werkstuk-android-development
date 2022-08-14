package com.example.cheapfreegames.ui.wishlist

import android.provider.Settings
import android.util.Log
import androidx.lifecycle.*
import com.example.cheapfreegames.database.WishlistGame
import com.example.cheapfreegames.database.WishlistGameDao
import com.example.cheapfreegames.database.WishlistGameRepository
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.model.ApiStatus
import com.example.cheapfreegames.network.model.GameLookupResult
import com.example.cheapfreegames.network.model.ListOfGamesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class WishlistViewModel(private val wishlistGameDao: WishlistGameDao) : ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _gameLookupResults = MutableLiveData<List<GameLookupResult>>()
    val gameLookupResults: LiveData<List<GameLookupResult>> = _gameLookupResults

    private val _repository: WishlistGameRepository = WishlistGameRepository(wishlistGameDao)

    private lateinit var _wishlistGames : List<WishlistGame>

    fun getWishlistedGames() {
        viewModelScope.launch(Dispatchers.Main) {
            _wishlistGames = _repository.getWishlistGames()
            fetchWishlistedGames()
        }
    }

    private fun fetchWishlistedGames(){
        viewModelScope.launch {
            try {
                _apiStatus.value = ApiStatus.LOADING

                _wishlistGames.forEach{ g ->
                    Log.i("api", "fetching game lookup by id ${g.gameId} ...")
                    val gameLookup = CheapSharkApi.retrofitService.getGameLookupById(g.gameId)
                    _gameLookupResults.value = gameLookupResults.value?.plus(gameLookup) ?: listOf(gameLookup)
                }

                Log.i("api", "${_gameLookupResults.value?.size} game lookup results found")

                _apiStatus.value = ApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _gameLookupResults.value = listOf()
                Log.e("api-error", "error fetching wishlisted games\n$e")
            }
        }
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