package com.example.cheapfreegames

import com.example.cheapfreegames.network.CheapSharkApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CheapSharkApiUnitTest {

    @Test
    fun getStores_notEmpty() = runTest{
        val stores = CheapSharkApi.retrofitService.getStoresInfo()
        assertTrue(stores.isNotEmpty())
    }

    @Test
    fun getListOfGamesByTitle_notEmpty() = runTest{
        val games = CheapSharkApi.retrofitService.getListOfGamesByTitle("Batman")
        assertTrue(games.isNotEmpty())
    }
}