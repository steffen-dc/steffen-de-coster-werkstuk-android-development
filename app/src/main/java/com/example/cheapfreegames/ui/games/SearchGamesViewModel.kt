package com.example.cheapfreegames.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchGamesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search games Fragment"
    }
    val text: LiveData<String> = _text
}