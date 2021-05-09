package com.spesolution.myapplication.util

import androidx.recyclerview.widget.DiffUtil
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonPaging

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
object PokemonConstant {
    const val ONE_TYPE_MONS = "ONE TYPE MONS"
    const val ONE_SKILL_MONS = "ONE SKILL MONS"
    val listPokemonAdapterCallback = object : DiffUtil.ItemCallback<PokemonPaging>() {
        override fun areItemsTheSame(oldItem: PokemonPaging, newItem: PokemonPaging): Boolean {
            return oldItem.pokemonName == newItem.pokemonName
        }

        override fun areContentsTheSame(oldItem: PokemonPaging, newItem: PokemonPaging): Boolean {
            return oldItem == newItem
        }
    }
}