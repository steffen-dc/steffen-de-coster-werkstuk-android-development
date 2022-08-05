package com.example.cheapfreegames.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://apidocs.cheapshark.com/
private const val BASE_URL = "https://www.cheapshark.com/api/1.0/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CheapSharkApiService {
    @GET("games")
    suspend fun getListOfGamesByTitle(@Query("title") title: String): List<ListOfGamesResult>
    @GET("games")
    suspend fun getGameLookupById(@Query("id") id: String): GameLookupResult
    @GET("deals")
    suspend fun getListOfDealsByTitle(@Query("title") title: String): List<ListOfDealsResult>
}

object CheapSharkApi {
    val retrofitService : CheapSharkApiService by lazy { // lazy = initialized at its first usage
        retrofit.create(CheapSharkApiService::class.java)
    }
}