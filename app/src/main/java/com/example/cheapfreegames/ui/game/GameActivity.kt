package com.example.cheapfreegames.ui.game

import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cheapfreegames.database.WishlistDatabase
import com.example.cheapfreegames.database.WishlistGameRepository
import com.example.cheapfreegames.databinding.ActivityGameBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[GameViewModel::class.java]
    }

    private val _repository : WishlistGameRepository by lazy {
        WishlistGameRepository(WishlistDatabase.getDatabase(application).wishlistGameDao())
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

        // check if game already in wishlist
        lifecycleScope.launch(Dispatchers.Main) {
            val wishlistGame = _repository.getWishlistGameByGameId(gameId)

            @Suppress("SENSELESS_COMPARISON")
            if (wishlistGame == null)
                binding.deleteWishlistActionButton.hide()
            else
                binding.addWishlistActionButton.hide()
        }

        // add game to wishlist
        binding.addWishlistActionButton.setOnClickListener { view ->
            binding.viewModel?.insertWishlistGame(gameId)
            Snackbar.make(view, "Game added to wishlist", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            binding.addWishlistActionButton.hide()
            binding.deleteWishlistActionButton.show()
        }

        // remove game from wishlist
        binding.deleteWishlistActionButton.setOnClickListener { view ->
            binding.viewModel?.deleteWishlistGame(gameId)
            Snackbar.make(view, "Game removed from wishlist", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            binding.addWishlistActionButton.show()
            binding.deleteWishlistActionButton.hide()
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