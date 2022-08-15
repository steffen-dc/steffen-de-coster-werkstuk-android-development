package com.example.cheapfreegames.ui.storedeals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cheapfreegames.network.model.ApiStatus
import com.example.cheapfreegames.network.model.ListOfDealsResult
import com.example.cheapfreegames.network.model.Store

class StoreDealsViewModel : ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _listOfDealsResults = MutableLiveData<List<ListOfDealsResult>>()
    val listOfDealsResults: LiveData<List<ListOfDealsResult>> = _listOfDealsResults

    private val _store = MutableLiveData<Store>()
    val store: LiveData<Store> = _store

    init{
    }
}