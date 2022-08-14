package com.example.cheapfreegames.network.model

data class ListOfGamesResult(
    val gameID: String? = "",
    val steamAppID: String? = "",
    val cheapest: String? = "",
    val cheapestDealID: String? = "",
    val external: String? = "",
    val thumb: String? = "",
)