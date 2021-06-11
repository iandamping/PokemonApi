package com.spesolution.myapplication.feature.paging.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.spesolution.myapplication.R
import com.spesolution.myapplication.databinding.PokemonLoadAdapterBinding

/**
 * Created by Ian Damping on 11,June,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonPagingLoadViewHolder(
    private val binding: PokemonLoadAdapterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PokemonPagingLoadViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_load_adapter, parent, false)
            val binding = PokemonLoadAdapterBinding.bind(view)
            return PokemonPagingLoadViewHolder(binding, retry)
        }
    }
}