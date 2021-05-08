package com.spesolution.myapplication.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun RecyclerView.horizontalRecyclerviewInitializer() {
    layoutManager = LinearLayoutManager(
        this.context, LinearLayoutManager.HORIZONTAL,
        false
    )
    if (this.onFlingListener == null) {
        RecyclerHorizontalSnapHelper()
            .attachToRecyclerView(this)
    }
}

fun RecyclerView.verticalRecyclerviewInitializer() {
    layoutManager = LinearLayoutManager(
        this.context, LinearLayoutManager.VERTICAL,
        false
    )
}

fun RecyclerView.gridRecyclerviewInitializer(size: Int) {
    layoutManager = GridLayoutManager(
        this.context, size
    )
}