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

    private val _listOfGamesResults = MutableLiveData<List<ListOfGamesResult>>()
    val listOfGamesResults: LiveData<List<ListOfGamesResult>> = _listOfGamesResults

    private val _repository: WishlistGameRepository = WishlistGameRepository(wishlistGameDao)

    private lateinit var _wishlistGames : List<WishlistGame>

    fun getWishlistedGames() {
        viewModelScope.launch(Dispatchers.Main) {
            // get gameIds from database
            _wishlistGames = _repository.getWishlistGames()

            // use gameIds from database to fetch games from api
            fetchWishlistedGames()
        }
    }

    private fun fetchWishlistedGames(){
        viewModelScope.launch {
            try {
                _apiStatus.value = ApiStatus.LOADING
                _listOfGamesResults.value = listOf()
                _wishlistGames.forEach{ g ->
                    Log.i("api", "fetching game lookup by id ${g.gameId} ...")
                    val gameLookup = CheapSharkApi.retrofitService.getGameLookupById(g.gameId)

                    // parse to ListOfGamesResult, so we can reuse ListOfGamesResultGridAdapter & list_of_games_result_grid_item.xml
                    val listOfGameResult = ListOfGamesResult(gameID = g.gameId, thumb = gameLookup.info?.thumb, external = gameLookup.info?.title, cheapest = gameLookup.deals?.first()?.price)

                    _listOfGamesResults.value = _listOfGamesResults.value?.plus(listOfGameResult)
                }

                Log.i("api", "${_listOfGamesResults.value?.size} game lookup results found")

                _apiStatus.value = ApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _listOfGamesResults.value = listOf()
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