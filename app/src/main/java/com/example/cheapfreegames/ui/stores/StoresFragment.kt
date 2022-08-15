package com.example.cheapfreegames.ui.stores

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cheapfreegames.databinding.FragmentStoresBinding

class StoresFragment : Fragment() {

    private val viewModel: StoresViewModel by viewModels()

    private var _binding: FragmentStoresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStoresBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.storesGrid.adapter = StoresGridAdapter{
            val action = StoresFragmentDirections.actionNavStoresToStoreDealsFragment(it.storeID, it.storeName)
            this.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        // get/refresh wishlist
        binding.viewModel?.fetchStores()
    }
}