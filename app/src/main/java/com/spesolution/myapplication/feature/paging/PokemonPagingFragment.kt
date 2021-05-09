package com.spesolution.myapplication.feature.paging

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.spesolution.myapplication.PokemonViewModel
import com.spesolution.myapplication.R
import com.spesolution.myapplication.core.domain.response.PokemonPaging
import com.spesolution.myapplication.databinding.FragmentPokemonPagingBinding
import com.spesolution.myapplication.module.CustomDialogQualifier
import com.spesolution.myapplication.util.gridRecyclerviewInitializer
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Ian Damping on 09,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@AndroidEntryPoint
class PokemonPagingFragment : Fragment(), PokemonPagingAdapter.PokemonPagingAdapterListener {
    @Inject
    lateinit var imageHelper: LoadImageHelper

    @Inject
    @CustomDialogQualifier
    lateinit var customDialog: AlertDialog

    private val vm: PokemonViewModel by viewModels()
    private var _binding: FragmentPokemonPagingBinding? = null
    private val binding get() = _binding!!

    private lateinit var pokemonAdapter: PokemonPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonAdapter = PokemonPagingAdapter(this, imageHelper)
        with(binding) {
            initView()
        }
        getData()
        consumeNavigation()
    }

    private fun consumeNavigation() {
        viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
            vm.navigationEventFlow.onEach { direction ->
                findNavController().navigate(direction)
            }.launchIn(this)
        }
    }

    private fun FragmentPokemonPagingBinding.initView() {
        rvPokemon.apply {
            gridRecyclerviewInitializer(2)
            adapter = pokemonAdapter

        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            pokemonAdapter.loadStateFlow.onEach { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.NotLoading -> customDialog.dismiss()
                    LoadState.Loading -> customDialog.show()
                    is LoadState.Error -> customDialog.dismiss()
                }
            }.launchIn(this)
        }
        imageHelper.loadWithGlide(ivBackgroundImage, R.drawable.ic_pokemon_bg_2)
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

    override fun onClicked(data: PokemonPaging) {
        val direction =
            PokemonPagingFragmentDirections.actionPokemonPagingFragmentToPokemonFragment(data.pokemonUrl)
        vm.setNavigationEventChannel(direction)
    }
}