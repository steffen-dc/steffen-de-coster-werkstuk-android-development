package com.example.cheapfreegames.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfGamesResults = MutableLiveData<List<ListOfGamesResult>>()
    val listOfGamesResults: LiveData<List<ListOfGamesResult>> = _listOfGamesResults

    private val _gameLookupResult = MutableLiveData<GameLookupResult?>()
    val gameLookupResult: LiveData<GameLookupResult?> = _gameLookupResult

    private val _listOfDealsResults = MutableLiveData<List<ListOfDealsResult>>()
    val listOfDealsResults: LiveData<List<ListOfDealsResult>> = _listOfDealsResults

    private val _dealLookupResult = MutableLiveData<DealLookupResult?>()
    val dealLookupResult: LiveData<DealLookupResult?> = _dealLookupResult

    init {
        getListOfGamesByTitle("batman")
        getGameLookupById("612")
        getListOfDealsByTitle("batman")
        getDealLookupById("X8sebHhbc1Ga0dTkgg59WgyM506af9oNZZJLU9uSrX8%3D")
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

    private fun getListOfDealsByTitle(title: String) {
        viewModelScope.launch {
            try {
                Log.i("api", "fetching list of deals by title $title ...")
                _listOfDealsResults.value = CheapSharkApi.retrofitService.getListOfDealsByTitle(title)
                Log.i("api", "size list of deals found by title $title: " + _listOfDealsResults.value?.size.toString())
            } catch (e: Exception) {
                Log.e("api-error", "error fetching list of deals by title $title \n$e")
                _listOfDealsResults.value = listOf()
            }
        }
    }

    private fun getDealLookupById(id: String) {
        viewModelScope.launch {
            try {
                Log.i("api", "fetching deal lookup by id $id ...")
                _dealLookupResult.value = CheapSharkApi.retrofitService.getDealLookupById(id)
//                Log.i("api", "size list of deal lookup found by id $id: " + _dealLookupResult.value?.size.toString())
                Log.i("api", "name of first deal lookup found by id $id: " + _dealLookupResult.value?.gameInfo?.name)
            } catch (e: Exception) {
                Log.e("api-error", "error fetching deal lookup by id $id \n$e")
                _dealLookupResult.value = null
            }
        }
    }
}