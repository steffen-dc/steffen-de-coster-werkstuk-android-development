package com.example.cheapfreegames.network

data class Store(
    val storeID: String?,
    val storeName: String?,
    val isActive: Boolean?,
    val images: List<Image>?,
)

// prefix = https://www.cheapshark.com/
data class Image(
    val banner: String?,
    val logo: String?,
    val icon: String?,
)
