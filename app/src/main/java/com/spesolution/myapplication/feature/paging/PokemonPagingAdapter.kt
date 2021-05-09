package com.spesolution.myapplication.feature.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.ItemPaginationPokemonBinding
import com.spesolution.myapplication.databinding.ItemPokemonBinding
import com.spesolution.myapplication.feature.PokemonViewHolder
import com.spesolution.myapplication.util.PokemonConstant
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonPagingAdapter(
    private val listener: PokemonPagingAdapterListener,
    private val loadImageHelper: LoadImageHelper
) :
    PagingDataAdapter<Pokemon, PokemonPagingViewHolder>(PokemonConstant.listPokemonAdapterCallback) {

    interface PokemonPagingAdapterListener {
        fun onClicked(data: Pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPagingViewHolder {
        return PokemonPagingViewHolder(
            ItemPaginationPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            loadImageHelper
        )
    }

    override fun onBindViewHolder(holder: PokemonPagingViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                listener.onClicked(data)
            }
        }
    }
}