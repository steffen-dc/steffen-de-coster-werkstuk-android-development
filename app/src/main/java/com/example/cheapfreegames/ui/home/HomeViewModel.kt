package com.example.cheapfreegames.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.GameLookupResult
import com.example.cheapfreegames.network.ListOfGamesResult
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfGamesResults = MutableLiveData<List<ListOfGamesResult>>()
    val listOfGamesResults: LiveData<List<ListOfGamesResult>> = _listOfGamesResults

    private val _gameLookupResult = MutableLiveData<GameLookupResult>()
    val gameLookupResult: LiveData<GameLookupResult> = _gameLookupResult

    init {
        getListOfGamesByTitle("batman")
        getGameLookupById("612")
    }

    private fun getListOfGamesByTitle(title: String) {
        viewModelScope.launch {
            try {
                _listOfGamesResults.value = CheapSharkApi.retrofitService.getListOfGamesByTitle(title)
            } catch (e: Exception) {
                Log.e("api-error", "error fetching list of games by title $title \n$e")
                _listOfGamesResults.value = listOf()
            }
        }
    }

    private fun getGameLookupById(id: String) {
        viewModelScope.launch {
            try {
                _gameLookupResult.value = CheapSharkApi.retrofitService.getGameLookupById(id)
            } catch (e: Exception) {
                Log.e("api-error", "error fetching game lookup by id $id \n$e")
            }
        }
    }
}