package com.spesolution.myapplication.module

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.spesolution.myapplication.databinding.CustomLoadingBinding
import com.spesolution.myapplication.util.layoutInflater
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

/**
 * Created by Ian Damping on 09,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Module
@InstallIn(ActivityComponent::class)
class LottieDialogModule {

    @Provides
    @CustomDialogQualifier
    fun provideLottieDialog(@ActivityContext context: Context): AlertDialog {
        val loadingBinding = CustomLoadingBinding.inflate(context.layoutInflater)
        loadingBinding.lottieAnimation.enableMergePathsForKitKatAndAbove(true)
        val dialogBuilder = AlertDialog.Builder(context).run {
            setView(loadingBinding.root)
        }
        return dialogBuilder.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }
}