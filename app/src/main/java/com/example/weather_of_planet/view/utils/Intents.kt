package com.example.weather_of_planet.view.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity

class Intents() {

    fun runIntent(context: Context, classs: Class<*>) {

        val intent = Intent(context, classs)
        val bundle = Bundle()
        startActivity(context, intent, bundle)

    }

    fun runIntentToWebCiew(context: Context, classs: Class<*>,url:String) {

        val intent = Intent(context, classs)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val bundle = Bundle()
        intent.putExtra("url",url)
        startActivity(context, intent, bundle)

    }

}