package com.example.cheapfreegames.ui.stores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.model.*
import kotlinx.coroutines.launch

class StoresViewModel : ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _stores = MutableLiveData<List<Store>>()
    val stores: LiveData<List<Store>> = _stores

    init{
        _stores.value = listOf()
    }

    fun fetchStores() {
        viewModelScope.launch {
            try {
                _apiStatus.value = ApiStatus.LOADING
                Log.i("api", "fetching stores ...")

                val stores = CheapSharkApi.retrofitService.getStoresInfo()

                // get active stores only
                _stores.value = stores.filter { s -> s.isActive == 1 }

                _apiStatus.value = ApiStatus.DONE
                Log.i("api", "size list of stores found: " + _stores.value?.size.toString())
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _stores.value = listOf()
                Log.e("api-error", "error fetching stores\n$e")
            }
        }
    }
}