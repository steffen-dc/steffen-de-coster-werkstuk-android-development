package com.example.cheapfreegames.network.model

data class ListOfDealsResult (
    val internalName: String?,
    val title: String?,
    val metacriticLink: String?,
    val dealID: String?,
    val storeID: String?,
    val gameID: String?,
    val salePrice: String?,
    val normalPrice: String?,
    val isOnSale: String?,
    val savings: String?,
    val metacriticScore: String?,
    val steamRatingText: String?,
    val steamRatingPercent: String?,
    val steamRatingCount: String?,
    val steamAppID: String?,
    val releaseDate: Int?,
    val lastChange: Int?,
    val dealRating: String?,
    val thumb: String?,
)