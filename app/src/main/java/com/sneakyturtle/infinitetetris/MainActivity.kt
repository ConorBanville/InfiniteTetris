package com.sneakyturtle.infinitetetris

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sneakyturtle.infinitetetris.activity.Game
import com.sneakyturtle.infinitetetris.databinding.LandingScreenBinding
import com.sneakyturtle.infinitetetris.storage.AppPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var b: LandingScreenBinding
    private lateinit var preferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_screen)

        b = LandingScreenBinding.inflate(layoutInflater).also { setContentView(it.root) }
        preferences = AppPreferences(this)
        b.btnNewGame.setOnClickListener { _ -> onBtnNewGameClick() }
    }

    private fun onBtnNewGameClick() {
        Log.d("Tetris:", "Starting activity: new game!")

        startActivity(Intent(this, Game::class.java))
    }
}