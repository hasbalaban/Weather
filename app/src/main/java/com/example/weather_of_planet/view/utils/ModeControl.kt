package com.example.weather_of_planet.view.utils

import android.content.Context
import com.example.weather_of_planet.R

class ModeControl() {
    fun setThema(context: Context):Int {
        val sharedManager = sharedPreferencesCreater(context)
        val mode = sharedManager.getDataBoolenFromSharedprefence("mode", false)
       val thema =  when(mode){
            true-> R.style.dark_mode
            else-> R.style.light_mode
        }
        return thema
    }

}