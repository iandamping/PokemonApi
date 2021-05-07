package com.spesolution.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.spesolution.myapplication.core.domain.model.DomainResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            vm.pokemon.onEach {
                when (it) {
                    is DomainResult.Data -> Timber.e("res : ${it.data}")
                    is DomainResult.Error -> Timber.e("res : ${it.message}")
                    DomainResult.Loading -> Timber.e("res empty")
                }
            }.launchIn(this)
        }
    }
}