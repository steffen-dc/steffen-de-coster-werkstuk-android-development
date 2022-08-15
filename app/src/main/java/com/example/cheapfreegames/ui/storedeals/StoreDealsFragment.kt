package com.example.cheapfreegames.ui.storedeals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cheapfreegames.databinding.FragmentStoreDealsBinding
import com.example.cheapfreegames.ui.searchgames.ListOfGamesResultGridAdapter

class StoreDealsFragment : Fragment() {

    private val viewModel: StoreDealsViewModel by viewModels()

    private var _binding: FragmentStoreDealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStoreDealsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.listOfDealsResultGrid.adapter = ListOfDealsResultGridAdapter()

        return binding.root
    }
}