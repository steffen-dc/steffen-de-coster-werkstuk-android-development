package com.example.cheapfreegames.ui.game

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cheapfreegames.databinding.ActivityGameBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[GameViewModel::class.java]
    }

    private var _binding: ActivityGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.dealsGrid.adapter = DealsGridAdapter()

        // get gameId from explicit intent
        val gameId = intent?.extras?.getString("gameId").toString()

        // fetch game data
        binding.viewModel?.getGame(gameId)

        // add game to wishlist
        binding.wishlistActionButton.setOnClickListener { view ->
            binding.viewModel?.insertWishlistGame(gameId)
            Snackbar.make(view, "Game added to wishlist", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // title bar back button goes to last fragment instead of main activity
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}