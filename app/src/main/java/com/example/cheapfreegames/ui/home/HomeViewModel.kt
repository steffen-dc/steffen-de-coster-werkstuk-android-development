package com.example.cheapfreegames.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.model.DealLookupResult
import com.example.cheapfreegames.network.model.ListOfDealsResult
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listOfDealsResults = MutableLiveData<List<ListOfDealsResult>>()
    val listOfDealsResults: LiveData<List<ListOfDealsResult>> = _listOfDealsResults

    private val _dealLookupResult = MutableLiveData<DealLookupResult?>()
    val dealLookupResult: LiveData<DealLookupResult?> = _dealLookupResult

    init {
//        getGameLookupById("612")
//        getListOfDealsByTitle("batman")
//        getDealLookupById("X8sebHhbc1Ga0dTkgg59WgyM506af9oNZZJLU9uSrX8%3D")
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