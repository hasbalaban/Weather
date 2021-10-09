package com.example.weather_of_planet.view.listeners

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ActivityMainBinding
import com.example.weather_of_planet.view.interfaces.Clickslistener

abstract class clickListenerForMainActivity() :  Clickslistener {
    override fun clickListener(view: View) {
        when (view.id) {
            R.id.fragment_Favorite -> Log.i("fragment", "fragment tıklandı")
        }

    }
}