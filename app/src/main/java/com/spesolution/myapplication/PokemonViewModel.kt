package com.spesolution.myapplication

import androidx.lifecycle.ViewModel
import com.spesolution.myapplication.core.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: PokemonRepository) : ViewModel() {

    val pokemon = repo.getPokemon()
}