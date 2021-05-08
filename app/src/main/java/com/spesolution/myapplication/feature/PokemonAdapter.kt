package com.spesolution.myapplication.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.ItemPokemonBinding
import com.spesolution.myapplication.util.PokemonConstant
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonAdapter(
    private val listener: PokemonAdapterListener,
    private val imageHelper: LoadImageHelper
) : ListAdapter<Pokemon, PokemonViewHolder>(PokemonConstant.listPokemonAdapterCallback) {

    interface PokemonAdapterListener {
        fun onClicked(data: Pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            imageHelper
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onClicked(data)
        }
    }
}