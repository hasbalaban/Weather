package com.example.weather_of_planet.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.DayItemForRecyclerviewBinding
import com.example.weather_of_planet.view.models.models_for_database.DayWeather
import com.example.weather_of_planet.view.utils.Intents
import com.example.weather_of_planet.view.utils.loadImage
import com.example.weather_of_planet.view.utils.sharedPreferencesCreater
import com.example.weather_of_planet.view.views.Activity_wevview


class adapter_for_item_of_day(val list_of_day: List<DayWeather>, val context: Context) :
    RecyclerView.Adapter<adapter_for_item_of_day.ViewHolder>() {

    class ViewHolder(val view: DayItemForRecyclerviewBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<DayItemForRecyclerviewBinding>(
            layoutInflater, R.layout.day_item_for_recyclerview,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val image_URL = list_of_day[position].image

        holder.view.WeatherIcon.loadImage(image_URL, context)

        holder.view.data = list_of_day[position]

        holder.view.AllDayDetails.setOnClickListener {
            val sharedManager = sharedPreferencesCreater(context)
            val city = sharedManager.getDataStringFromSharedprefence("defaultCity","Ankara")
            val url = "https://api.weatherapi.com/v1/forecast.json?key=c0e3565ced6b4faf952144140212909&q=${city.toLowerCase()}&days=7&aqi=yes&alerts=yes"
            Intents().runIntentToWebCiew(context, Activity_wevview::class.java,url)
        }


    }


    override fun getItemCount(): Int {
        return list_of_day.size
    }


}