package com.spesolution.myapplication.util.imageHelper

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * Created by Ian Damping on 01,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface LoadImageHelper {
    fun loadWithGlide(view: ImageView, url: String?)
    fun loadWithGlide(view: ImageView, @DrawableRes drawable: Int)
}