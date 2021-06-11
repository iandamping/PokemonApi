package com.spesolution.myapplication.feature.paging.loading_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.spesolution.myapplication.databinding.PokemonLoadAdapterBinding

/**
 * Created by Ian Damping on 11,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonPagingLoadAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonPagingLoadViewHolder>() {
    override fun onBindViewHolder(holder: PokemonPagingLoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PokemonPagingLoadViewHolder {
        return PokemonPagingLoadViewHolder(
            PokemonLoadAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )
        // return PokemonPagingLoadViewHolder.create(parent, retry)
    }
}