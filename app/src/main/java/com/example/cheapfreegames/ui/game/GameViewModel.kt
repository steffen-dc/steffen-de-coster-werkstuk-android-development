package com.example.cheapfreegames.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.GameLookupResult
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val _gameLookupResult = MutableLiveData<GameLookupResult?>()
    val gameLookupResult: LiveData<GameLookupResult?> = _gameLookupResult


    fun getGame(gameId: String){
        getGameLookupById(gameId)
    }

    private fun getGameLookupById(id: String) {
        viewModelScope.launch {
            try {
                Log.i("api", "fetching game lookup by id $id ...")
                _gameLookupResult.value = CheapSharkApi.retrofitService.getGameLookupById(id)
                Log.i("api", "title of game lookup found by id $id: " + _gameLookupResult.value?.info?.title)
            } catch (e: Exception) {
                Log.e("api-error", "error fetching game lookup by id $id \n$e")
                _gameLookupResult.value = null
            }
        }
    }
}