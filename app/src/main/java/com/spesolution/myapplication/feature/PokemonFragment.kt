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
import com.spesolution.myapplication.PokemonViewModel
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.FragmentPokemonBinding
import com.spesolution.myapplication.util.horizontalRecyclerviewInitializer
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper
import dagger.hilt.android.AndroidEntryPoint
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
class PokemonFragment : Fragment(), PokemonAdapter.PokemonAdapterListener {
    @Inject
    lateinit var imageHelper: LoadImageHelper

    private val vm: PokemonViewModel by viewModels()
    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    private lateinit var pokemonAdapter: PokemonAdapter

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
        pokemonAdapter = PokemonAdapter(this, imageHelper)
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
    }

    private fun getData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.pokemon.onEach {
                when (it) {
                    is DomainResult.Data -> {
                        binding.pbPokemon.isVisible = false
                        pokemonAdapter.submitList(it.data)
                    }
                    is DomainResult.Error -> {
                        binding.pbPokemon.isVisible = false
                        consumeError(it.message)
                    }
                    DomainResult.Loading -> binding.pbPokemon.isVisible = true
                }
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