package com.spesolution.myapplication.feature.paging

import androidx.recyclerview.widget.RecyclerView
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.ItemPaginationPokemonBinding
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 09,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonPagingViewHolder(
    private val binding: ItemPaginationPokemonBinding,
    private val loadImageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pokemon) {
        with(binding) {
            loadImageHelper.loadWithGlide(ivPokemonImage, data.pokemonImage)
            tvPokemonName.text = data.pokemonName
        }
    }
}