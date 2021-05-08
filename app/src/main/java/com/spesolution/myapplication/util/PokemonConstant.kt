package com.spesolution.myapplication.util

import androidx.recyclerview.widget.DiffUtil
import com.spesolution.myapplication.core.domain.model.Pokemon

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
object PokemonConstant {
    const val ONE_TYPE_MONS = "ONE TYPE MONS"
    val listPokemonAdapterCallback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.pokemonName == newItem.pokemonName
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
}