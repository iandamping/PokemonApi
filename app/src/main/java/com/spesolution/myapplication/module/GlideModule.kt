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

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@InstallIn(ActivityComponent::class)
@Module
object GlideModule {

    @Provides
    fun provideRequestManager(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .format(DecodeFormat.PREFER_RGB_565)
            // .placeholder(R.drawable.empty_image)
            .error(R.drawable.no_data)
    }
}