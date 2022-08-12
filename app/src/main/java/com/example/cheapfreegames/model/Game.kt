package com.example.cheapfreegames.model

import com.example.cheapfreegames.network.model.CheapestPriceEver

data class Game (
    val title: String?,
    val image: String?,
    val storeDeals: List<StoreDeal>?,
)
