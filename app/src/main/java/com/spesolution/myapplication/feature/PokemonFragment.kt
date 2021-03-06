package com.spesolution.myapplication.feature

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.spesolution.myapplication.core.domain.model.DomainResult
import com.spesolution.myapplication.core.domain.response.PokemonDetail
import com.spesolution.myapplication.core.domain.response.PokemonDetailSpecies
import com.spesolution.myapplication.databinding.FragmentPokemonDetailBinding
import com.spesolution.myapplication.module.CustomDialogQualifier
import com.spesolution.myapplication.util.PokemonConstant.ONE_EGG_MONS
import com.spesolution.myapplication.util.PokemonConstant.ONE_SKILL_MONS
import com.spesolution.myapplication.util.PokemonConstant.ONE_TYPE_MONS
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@AndroidEntryPoint
class PokemonFragment @Inject constructor(
    private val imageHelper: LoadImageHelper,
    @CustomDialogQualifier private val customDialog: AlertDialog
) : Fragment() {

    private val args by navArgs<PokemonFragmentArgs>()

    private val vm: PokemonViewModel by viewModels()
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData(args.pokemonUrl)
    }

    private fun FragmentPokemonDetailBinding.initView(data: PokemonDetail) {
        imageHelper.loadWithGlide(ivItemPokemonImage, data.pokemonImage)
        imageHelper.loadWithGlide(ivItemSmallPokemonImage1, data.pokemonSmallImage1)
        imageHelper.loadWithGlide(ivItemSmallPokemonImage2, data.pokemonSmallImage3)
        imageHelper.loadWithGlide(ivItemSmallPokemonImage3, data.pokemonSmallImage2)
        imageHelper.loadWithGlide(ivItemSmallPokemonImage4, data.pokemonSmallImage4)
        tvStatName0.text = data.pokemonStat0.name
        tvStatPoint0.text = data.pokemonStat0.point.toString()
        tvStatName1.text = data.pokemonStat1.name
        tvStatPoint1.text = data.pokemonStat1.point.toString()
        tvStatName2.text = data.pokemonStat2.name
        tvStatPoint2.text = data.pokemonStat2.point.toString()
        tvStatName3.text = data.pokemonStat3.name
        tvStatPoint3.text = data.pokemonStat3.point.toString()
        tvStatName4.text = data.pokemonStat4.name
        tvStatPoint4.text = data.pokemonStat4.point.toString()
        tvStatName5.text = data.pokemonStat5.name
        tvStatPoint5.text = data.pokemonStat5.point.toString()
        tvType0.text = data.pokemonType0
        if (data.pokemonType1 == ONE_TYPE_MONS) {
            llType1.visibility = View.GONE
        } else {
            llType1.visibility = View.VISIBLE
            tvType1.text = data.pokemonType1
        }

        tvAbility0.text = data.pokemonAbility1
        if (data.pokemonAbility2 == ONE_SKILL_MONS) {
            llAbilities1.visibility = View.GONE
        } else {
            llAbilities1.visibility = View.VISIBLE
            tvAbility1.text = data.pokemonAbility2
        }
    }

    private fun FragmentPokemonDetailBinding.initSpeciesView(data: PokemonDetailSpecies) {
        tvGenerationName.text = data.generation
        tvGrowthRate.text = data.growthRate
        tvHappinesPoint.text = data.happines.toString()
        tvCaptureRate.text = data.captureRate.toString()
        tvPokemonColor.text = data.color
        tvHabitatName.text = data.habitat
        tvShapeName.text = data.shape
        tvEgg0.text = data.eggGroup1
        if (data.eggGroup2 == ONE_EGG_MONS) {
            llEgg1.visibility = View.GONE
        } else {
            llEgg1.visibility = View.VISIBLE
            tvEgg1.text = data.eggGroup2
        }
    }

    private fun getData(url: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.pokemonDetail(url).flatMapMerge { detailPokemon ->
                binding.initView(data = detailPokemon)
                vm.pokemonSpeciesDetail(detailPokemon.pokemonSpeciesUrl)
            }.onEach { detailPokemonSpecies ->
                binding.initSpeciesView(detailPokemonSpecies)
            }.catch {
                consumeError(it.localizedMessage ?: "Error happen")
            }.onStart {
                customDialog.show()
            }.onCompletion {
                customDialog.dismiss()
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
}