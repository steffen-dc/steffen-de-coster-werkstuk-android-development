package com.example.cheapfreegames.ui.searchgames

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.ListOfGamesResult
import kotlinx.coroutines.launch

class SearchGamesViewModel : ViewModel() {

    private val _listOfGamesResults = MutableLiveData<List<ListOfGamesResult>>()
    val listOfGamesResults: LiveData<List<ListOfGamesResult>> = _listOfGamesResults

    init {
        _listOfGamesResults.value = listOf()
    }

    fun searchGame(title: String){
        if (title.isBlank()) _listOfGamesResults.value = listOf()
        getListOfGamesByTitle(title)
    }

    private fun getListOfGamesByTitle(title: String) {
        viewModelScope.launch {
            try {
                Log.i("api", "fetching list of games by title $title ...")
                _listOfGamesResults.value = CheapSharkApi.retrofitService.getListOfGamesByTitle(title)
                Log.i("api", "size list of games found by title $title: " + _listOfGamesResults.value?.size.toString())
            } catch (e: Exception) {
                Log.e("api-error", "error fetching list of games by title $title \n$e")
                _listOfGamesResults.value = listOf()
            }
        }
    }
}