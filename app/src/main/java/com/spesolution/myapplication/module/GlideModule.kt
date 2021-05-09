package com.spesolution.myapplication.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.spesolution.myapplication.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@InstallIn(ActivityComponent::class)
@Module
object GlideModule {

    @Provides
    @ActivityScoped
    fun provideRequestManager(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @ActivityScoped
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .format(DecodeFormat.PREFER_RGB_565)
            .error(R.drawable.no_data)
    }
}