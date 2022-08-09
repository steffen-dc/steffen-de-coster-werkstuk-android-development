package com.example.cheapfreegames.ui.game

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.cheapfreegames.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get gameId from explicit intent
        val gameId = intent?.extras?.getString("gameId").toString()

        title = gameId // for testing
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