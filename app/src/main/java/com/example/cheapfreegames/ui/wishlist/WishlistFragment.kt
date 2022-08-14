package com.example.cheapfreegames.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.cheapfreegames.CheapGamesApplication
import com.example.cheapfreegames.databinding.FragmentWishlistBinding
import com.example.cheapfreegames.ui.searchgames.ListOfGamesResultGridAdapter

class WishlistFragment : Fragment() {

    private val viewModel: WishlistViewModel by activityViewModels {
        WishlistViewModelFactory((activity?.application as CheapGamesApplication).database.wishlistGameDao())
    }
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishlistBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Give the binding access to the WishlistViewModel
        binding.viewModel = viewModel

        binding.listOfGamesResultGrid.adapter = ListOfGamesResultGridAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}