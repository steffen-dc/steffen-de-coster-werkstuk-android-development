package com.example.cheapfreegames

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

class Util {
    companion object{
        fun hideKeyboard(fragment: Fragment) {
            val inputMethodManager = fragment.requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                    InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(fragment.requireActivity().currentFocus?.windowToken, 0)
        }
    }
}