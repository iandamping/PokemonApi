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
import com.spesolution.myapplication.R
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
import kotlinx.coroutines.flow.collect
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
    private var isFavorite: Boolean = false
    private var idForDeleteItem: Int? = null
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
        observeBookmarkState(binding)
    }

    private fun FragmentPokemonDetailBinding.initView(data: PokemonDetail) {
        with(imageHelper){
            loadWithGlide(ivFavorite, R.drawable.ic_baseline_star_outline_24)
            loadWithGlide(ivItemPokemonImage, data.pokemonImage)
            loadWithGlide(ivItemSmallPokemonImage1, data.pokemonSmallImage1)
            loadWithGlide(ivItemSmallPokemonImage2, data.pokemonSmallImage3)
            loadWithGlide(ivItemSmallPokemonImage3, data.pokemonSmallImage2)
            loadWithGlide(ivItemSmallPokemonImage4, data.pokemonSmallImage4)
        }

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
        observeDatabase(data.pokemonName)

        ivFavorite.setOnClickListener {
            if (isFavorite) {
                if (idForDeleteItem != null) vm.clearFavorite(idForDeleteItem!!)
            } else {
                vm.saveFavorite(data)
            }
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

    private fun observeDatabase(pokemonName:String){
        lifecycleScope.launchWhenStarted {
            vm.listFavorite.collect { favData ->
                favData.firstOrNull { it.pokemonName == pokemonName }.let { singleData ->
                    idForDeleteItem = if (singleData!=null){
                        vm.setBookmarkState(singleData.pokemonName == pokemonName)
                        singleData.pokemonFavoriteId
                    } else{
                        vm.setBookmarkState(false)
                        null
                    }
                }
            }
        }
    }

    private fun observeBookmarkState(binding: FragmentPokemonDetailBinding){
        lifecycleScope.launchWhenStarted {
            vm.bookmarkStateFlow.collect { state ->
                if (state){
                    imageHelper.loadWithGlide(binding.ivFavorite, R.drawable.ic_baseline_star_rate_24)
                } else imageHelper.loadWithGlide(binding.ivFavorite, R.drawable.ic_baseline_star_outline_24)

                isFavorite = state
            }
        }
    }

}