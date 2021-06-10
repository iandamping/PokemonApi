package com.spesolution.myapplication.feature.state

import com.spesolution.myapplication.core.domain.model.DomainResult

/**
 * Created by Ian Damping on 25,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class PokemonState<out T>  {
    object Loading:PokemonState<Nothing>()
    data class Data<out T>(val data: T) : PokemonState<T>()
    data class Error(val message: String) : PokemonState<Nothing>()
}