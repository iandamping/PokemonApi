package com.spesolution.myapplication.module.factory

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface DefaultFragmentFactoryEntryPoint {
    fun getFragmentFactory(): DefaultFragmentFactory
}