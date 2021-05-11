package com.spesolution.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spesolution.myapplication.databinding.ActivityMainBinding
import com.spesolution.myapplication.feature.PokemonFragment
import com.spesolution.myapplication.feature.paging.PokemonPagingFragment
import com.spesolution.myapplication.module.factory.DefaultFragmentFactoryEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val entryPoint =
            EntryPointAccessors.fromActivity(
                this,
                DefaultFragmentFactoryEntryPoint::class.java
            )

        supportFragmentManager.fragmentFactory = entryPoint.getFragmentFactory()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}