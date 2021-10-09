package com.example.weather_of_planet.view.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weather_of_planet.R


// extention function to use for ImageView
fun ImageView.loadImage(
    URL: String,
    context: Context
) {
    val real_URL = URL

    val progressDrawable = CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }

    val options = RequestOptions
        .placeholderOf(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(real_URL).into(this)


}

//just do
fun ImageView.NoImage (){

    val progressDrawable = CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }

    val options = RequestOptions
        .placeholderOf(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(progressDrawable).into(this)


}
