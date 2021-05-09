package com.spesolution.myapplication.util.imageHelper

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class LoadImageHelperImpl @Inject constructor(
    private val requestManager: RequestManager,
    private val requestOptions: RequestOptions
) : LoadImageHelper {

    override fun loadWithGlide(view:ImageView, url: String?) {
        requestManager
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .thumbnail(0.25f).into(view)
    }

    override fun loadWithGlide(view: ImageView, @DrawableRes drawable: Int) {
        requestManager
            .load(drawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .thumbnail(0.25f).into(view)
    }
}