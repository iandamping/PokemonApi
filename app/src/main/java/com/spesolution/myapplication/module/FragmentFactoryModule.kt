package com.spesolution.myapplication.module

import androidx.fragment.app.Fragment
import com.spesolution.myapplication.feature.PokemonFragment
import com.spesolution.myapplication.feature.paging.PokemonPagingFragment
import com.spesolution.myapplication.module.factory.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

/**
 * Created by Ian Damping on 17,April,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(SingletonComponent::class)
interface FragmentFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(PokemonFragment::class)
    fun getPokemonFragment(fragment: PokemonFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PokemonPagingFragment::class)
    fun getPokemonPagingFragment(fragment: PokemonPagingFragment): Fragment
}