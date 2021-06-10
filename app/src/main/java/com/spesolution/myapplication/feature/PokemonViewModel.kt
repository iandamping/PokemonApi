package com.spesolution.myapplication.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.paging.cachedIn
import com.spesolution.myapplication.core.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(private val useCase: PokemonUseCase) : ViewModel() {

    private val navigationEventChannel = Channel<NavDirections>(Channel.CONFLATED)
    val navigationEventFlow = navigationEventChannel.receiveAsFlow()

    val pokemonPaging =
        useCase.getPagingPokemon().cachedIn(viewModelScope)

    fun pokemonDetail(url: String) = useCase.getDetailPokemon(url)

    fun pokemonSpeciesDetail(url: String) = useCase.getDetailSpeciesPokemon(url)

    fun setNavigationEventChannel(directions: NavDirections) {
        viewModelScope.launch {
            navigationEventChannel.send(directions)
        }
    }
}