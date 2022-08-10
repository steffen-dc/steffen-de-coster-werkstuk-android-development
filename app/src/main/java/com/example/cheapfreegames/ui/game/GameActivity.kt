package com.example.cheapfreegames.ui.game

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.example.cheapfreegames.databinding.ActivityGameBinding
import com.example.cheapfreegames.network.GameLookupResult
import com.example.cheapfreegames.ui.searchgames.ListOfGamesResultGridAdapter

class GameActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.dealsGrid.adapter = DealsGridAdapter()

        // get gameId from explicit intent
        val gameId = intent?.extras?.getString("gameId").toString()

        // fetch game data
        binding.viewModel?.getGame(gameId)

        title = gameId // for testing

        // create observer to update ui after fetching data from api
//        val gameLookupResultObserver = Observer<GameLookupResult?> { gameLookupResult ->
//            // update the ui
//            binding.gameTitle.text = gameLookupResult.info?.title
//            binding.gameImage.load(gameLookupResult.info?.thumb)
//        }
//
//        viewModel.gameLookupResult.observe(this, gameLookupResultObserver)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // title bar back button goes to last fragment instead of main activity
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}