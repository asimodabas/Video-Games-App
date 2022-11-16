package com.asimodabas.appcent_interview.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asimodabas.appcent_interview.loadImage

class GameBindingAdapter {
    companion object {
        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, gameImageId: String) {
            imageView.loadImage(gameImageId)
        }
    }
}