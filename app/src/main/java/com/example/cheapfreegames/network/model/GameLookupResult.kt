package com.example.cheapfreegames.network.model

data class GameLookupResult (
    val info: Info?,
    val cheapestPriceEver: CheapestPriceEver?,
    val deals: List<Deal>?,
)

data class Info(
    val title: String?,
    val steamAppID: String?,
    val thumb: String?,
)
data class CheapestPriceEver(
    val price: String?,
    val date: Int?,
)
data class Deal(
    val storeID: String?,
    val dealID: String?,
    val price: String?,
    val retailPrice: String?,
    val savings: String?,
)