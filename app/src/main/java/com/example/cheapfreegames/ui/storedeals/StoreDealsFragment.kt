package com.example.cheapfreegames.ui.storedeals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cheapfreegames.R
import com.example.cheapfreegames.databinding.FragmentStoreDealsBinding

class StoreDealsFragment : Fragment() {

    private val viewModel: StoreDealsViewModel by viewModels()

    private var _binding: FragmentStoreDealsBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: StoreDealsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStoreDealsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.listOfDealsResultGrid.adapter = ListOfDealsResultGridAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (navigationArgs.storeId == null) return
        binding.viewModel?.fetchStoreWithTopDeals(navigationArgs.storeId!!)

        binding.apply {
            topDealsLabel.text = String.format(getString(R.string.top_deals_on_store), navigationArgs.storeName)
        }
    }
}