package com.example.cheapfreegames.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cheapfreegames.databinding.FragmentSearchgamesBinding

class SearchGamesFragment : Fragment() {

    private val viewModel: SearchGamesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentSearchgamesBinding.inflate(inflater) // string
        // val binding = GridViewItemBinding.inflate(inflater) // image

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Give the binding access to the SearchGamesViewModel
        binding.viewModel = viewModel

        binding.listOfGamesResultGrid.adapter = ListOfGamesResultGridAdapter()

        return binding.root
    }
}