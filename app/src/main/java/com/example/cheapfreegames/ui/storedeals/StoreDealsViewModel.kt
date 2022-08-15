package com.example.cheapfreegames.ui.storedeals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.network.CheapSharkApi
import com.example.cheapfreegames.network.model.ApiStatus
import com.example.cheapfreegames.network.model.ListOfDealsResult
import com.example.cheapfreegames.network.model.Store
import kotlinx.coroutines.launch

class StoreDealsViewModel : ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _store = MutableLiveData<Store>()
    val store: LiveData<Store> = _store

    private val _listOfDealsResults = MutableLiveData<List<ListOfDealsResult>>()
    val listOfDealsResults: LiveData<List<ListOfDealsResult>> = _listOfDealsResults

    init{
    }

    fun fetchStoreWithTopDeals(storeId: String) {
        viewModelScope.launch {
            try {
                _apiStatus.value = ApiStatus.LOADING
                Log.i("api", "fetching stores ...")

                val stores = CheapSharkApi.retrofitService.getStoresInfo()
                // get store matching storeId
                _store.value = stores.firstOrNull { s -> s.storeID == storeId }

                Log.i("api", "fetching top deals for store ${_store.value?.storeName} ...")
                _listOfDealsResults.value = CheapSharkApi.retrofitService.getListOfDealsByStoreId(storeId, "20", "60")
                Log.i("api", "size list of deals found: " + _listOfDealsResults.value?.size.toString())

                _apiStatus.value = ApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _listOfDealsResults.value = listOf()
                Log.e("api-error", "error fetching top deals for store with id $storeId\n$e")
            }
        }
    }
}