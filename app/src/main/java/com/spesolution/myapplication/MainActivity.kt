package com.spesolution.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spesolution.myapplication.databinding.ActivityMainBinding
import com.spesolution.myapplication.feature.PokemonFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPokemon()
    }

    private fun setupPokemon() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainNavHostFr, PokemonFragment())
            .commit()
    }
}