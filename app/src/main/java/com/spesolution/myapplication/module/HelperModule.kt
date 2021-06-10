package com.spesolution.myapplication.module

import com.spesolution.myapplication.util.imageHelper.LoadImageHelper
import com.spesolution.myapplication.util.imageHelper.LoadImageHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@InstallIn(ActivityComponent::class)
@Module
interface HelperModule {

    @Binds
    @ActivityScoped
    fun bindsLoadImageHelper(loadImageHelperImpl: LoadImageHelperImpl): LoadImageHelper
}