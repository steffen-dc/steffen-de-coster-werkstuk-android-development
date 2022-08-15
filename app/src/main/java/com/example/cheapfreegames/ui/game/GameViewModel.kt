package com.example.cheapfreegames.ui.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.cheapfreegames.R
import com.example.cheapfreegames.database.WishlistDatabase
import com.example.cheapfreegames.database.WishlistGame
import com.example.cheapfreegames.database.WishlistGameRepository
import com.example.cheapfreegames.model.Game
import com.example.cheapfreegames.model.StoreDeal
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.model.ApiStatus
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val _repository: WishlistGameRepository

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _game = MutableLiveData<Game?>()
    val game: LiveData<Game?> = _game

    init{
        val dao = WishlistDatabase.getDatabase(application).wishlistGameDao()
        _repository = WishlistGameRepository(dao)
    }

    fun getGame(gameId: String){
        getGameWithStoreDeals(gameId)
    }

    fun insertWishlistGame(gameId: String){
        viewModelScope.launch {
            _repository.insertWishlistGame(WishlistGame(gameId = gameId))
        }
    }

    fun deleteWishlistGame(gameId: String) {
        viewModelScope.launch {
            _repository.deleteWishlistGame(gameId)
        }
    }

    private fun getGameWithStoreDeals(gameId: String){
        viewModelScope.launch {
            try {
                _apiStatus.value = ApiStatus.LOADING

                // fetch game lookup
                Log.i("api", "fetching game lookup by id $gameId ...")
                val gameLookup = CheapSharkApi.retrofitService.getGameLookupById(gameId)
                Log.i("api", "title of game lookup found by id $gameId: " + gameLookup.info?.title)

                // fetch store info
                Log.i("api", "fetching stores info ...")
                val stores = CheapSharkApi.retrofitService.getStoresInfo()

                // link game to store
                val storeDeals = mutableListOf<StoreDeal>()
                gameLookup.deals?.forEach{ d ->
                    val store = stores.find { s ->  s.storeID == d.storeID && s.isActive == 1 } ?: return@forEach
                    val storeDeal = StoreDeal(store.storeID, store.storeName, "https://www.cheapshark.com/${store.images?.banner}", "https://www.cheapshark.com/${store.images?.logo}", "https://www.cheapshark.com/${store.images?.icon}", d.dealID, store.isActive, d.price, d.retailPrice, d.savings)
                    storeDeals += storeDeal
                }
                _game.value = Game(gameLookup.info?.title, gameLookup.info?.thumb, storeDeals)

                _apiStatus.value = ApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                Log.e("api-error", "error fetching game by id $gameId \n$e")
                _game.value = null
            }
        }
    }
}