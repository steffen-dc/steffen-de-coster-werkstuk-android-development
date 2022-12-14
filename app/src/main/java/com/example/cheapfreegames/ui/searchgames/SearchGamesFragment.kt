package com.example.cheapfreegames.ui.searchgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cheapfreegames.Util
import com.example.cheapfreegames.databinding.FragmentSearchGamesBinding

class SearchGamesFragment : Fragment() {

    private val viewModel: SearchGamesViewModel by viewModels()

    // Binding object instance corresponding to the fragment_search_games.xml layout
    private var _binding: FragmentSearchGamesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchGamesBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Give the binding access to the SearchGamesViewModel
        binding.viewModel = viewModel

        binding.listOfGamesResultGrid.adapter = ListOfGamesResultGridAdapter()

        // set search action listener
        binding.searchAction.setOnClickListener{
            searchGame()
        }

        return binding.root
    }

    private fun searchGame() {
        binding.viewModel?.searchGame(binding.gameTitle.text.toString())
        Util.hideKeyboard(this)
    }
}