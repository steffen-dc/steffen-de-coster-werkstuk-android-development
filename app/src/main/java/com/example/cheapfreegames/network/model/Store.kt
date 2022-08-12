package com.example.cheapfreegames.network.model

data class Store(
    val storeID: String?,
    val storeName: String?,
    val isActive: Boolean?,
    val images: Image?,
)

// prefix = https://www.cheapshark.com/
data class Image(
    val banner: String?,
    val logo: String?,
    val icon: String?,
)
