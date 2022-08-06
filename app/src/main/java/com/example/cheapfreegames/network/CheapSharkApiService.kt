package com.example.cheapfreegames.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://apidocs.cheapshark.com/
private const val BASE_URL = "https://www.cheapshark.com/api/1.0/"

private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()


interface CheapSharkApiService {
    @GET("games")
    suspend fun getListOfGamesByTitle(@Query("title") title: String): List<ListOfGamesResult>
    @GET("games")
    suspend fun getGameLookupById(@Query("id") id: String): GameLookupResult
    @GET("deals")
    suspend fun getListOfDealsByTitle(@Query("title") title: String): List<ListOfDealsResult>
    @GET("deals")
    suspend fun getDealLookupById(@Query("id") id: String): DealLookupResult
}

object CheapSharkApi {
    val retrofitService : CheapSharkApiService by lazy { // lazy = initialized at its first usage
        retrofit.create(CheapSharkApiService::class.java)
    }
}