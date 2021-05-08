package com.spesolution.myapplication.feature

import androidx.recyclerview.widget.RecyclerView
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.ItemPokemonBinding
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonViewHolder(
    private val binding: ItemPokemonBinding,
    private val imageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pokemon) {
        with(binding) {
            imageHelper.loadWithGlide(ivItemPokemonImage, data.pokemonImage)
            imageHelper.loadWithGlide(ivItemSmallPokemonImage1, data.pokemonSmallImage1)
            imageHelper.loadWithGlide(ivItemSmallPokemonImage2, data.pokemonSmallImage3)
            imageHelper.loadWithGlide(ivItemSmallPokemonImage3, data.pokemonSmallImage2)
            imageHelper.loadWithGlide(ivItemSmallPokemonImage4, data.pokemonSmallImage4)
            tvItemPokemonName.text = data.pokemonName
        }
    }
}