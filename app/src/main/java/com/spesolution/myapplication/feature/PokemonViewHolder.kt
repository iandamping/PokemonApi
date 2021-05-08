package com.spesolution.myapplication.feature

import android.opengl.Visibility
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spesolution.myapplication.core.domain.model.Pokemon
import com.spesolution.myapplication.databinding.ItemPokemonBinding
import com.spesolution.myapplication.util.PokemonConstant.ONE_SKILL_MONS
import com.spesolution.myapplication.util.PokemonConstant.ONE_TYPE_MONS
import com.spesolution.myapplication.util.imageHelper.LoadImageHelper

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PokemonViewHolder(
    private val binding: ItemPokemonBinding,
    private val imageHelper: LoadImageHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pokemon) {
        with(binding) {
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

            tvType0.text = data.pokemonType0
            if (data.pokemonType1 == ONE_TYPE_MONS){
                llType1.visibility = View.GONE
            } else {
                llType1.visibility = View.VISIBLE
                tvType1.text = data.pokemonType1
            }

            tvAbility0.text = data.pokemonAbility1
            if (data.pokemonAbility2 == ONE_SKILL_MONS){
                llAbilities1.visibility = View.GONE
            } else {
                llAbilities1.visibility = View.VISIBLE
                tvAbility1.text = data.pokemonAbility2
            }


        }
    }
}