package com.example.cheapfreegames.ui.searchgames

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.ApiStatus
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.ListOfGamesResult
import kotlinx.coroutines.launch

class SearchGamesViewModel : ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

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
                _apiStatus.value = ApiStatus.LOADING
                Log.i("api", "fetching list of games by title $title ...")
                _listOfGamesResults.value = CheapSharkApi.retrofitService.getListOfGamesByTitle(title)
                _apiStatus.value = ApiStatus.DONE
                Log.i("api", "size list of games found by title $title: " + _listOfGamesResults.value?.size.toString())
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _listOfGamesResults.value = listOf()
                Log.e("api-error", "error fetching list of games by title $title \n$e")
            }
        }
    }
}