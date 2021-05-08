package com.spesolution.myapplication.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.spesolution.myapplication.PokemonViewModel
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.FragmentPokemonBinding
import com.spesolution.myapplication.feature.paging.PokemonPagingAdapter
import com.spesolution.myapplication.util.horizontalRecyclerviewInitializer
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@AndroidEntryPoint
class PokemonFragment : Fragment(), PokemonPagingAdapter.PokemonPagingAdapterListener {
    @Inject
    lateinit var imageHelper: LoadImageHelper

    private val vm: PokemonViewModel by viewModels()
    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    private lateinit var pokemonAdapter: PokemonPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonAdapter = PokemonPagingAdapter(this, imageHelper)
        with(binding) {
            initView()
        }
        getData()
    }

    private fun FragmentPokemonBinding.initView() {
        rvPokemon.apply {
            horizontalRecyclerviewInitializer()
            adapter = pokemonAdapter

        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted{
            pokemonAdapter.loadStateFlow.onEach { loadState ->
                // Only show the list if refresh succeeds.
                rvPokemon.isVisible = loadState.source.refresh is LoadState.NotLoading
                // Show loading spinner during initial load or refresh.
                pbPokemon.isVisible = loadState.source.refresh is LoadState.Loading
            }.launchIn(this)
        }

    }

    private fun getData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.pokemonPaging.onEach {
                pokemonAdapter.submitData(it)
            }.launchIn(this)
        }
    }

    private fun consumeError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClicked(data: Pokemon) {
        Timber.e("clicked : $data")
    }
}