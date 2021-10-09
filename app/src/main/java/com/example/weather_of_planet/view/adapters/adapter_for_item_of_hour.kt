package com.example.weather_of_planet.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.HourItemForRecyclerviewBinding
import com.example.weather_of_planet.view.models.model_of_weather_of_7days.Hour
import com.example.weather_of_planet.view.models.createNewModelForHour
import com.example.weather_of_planet.view.models.models_for_database.Temp_with_image
import com.example.weather_of_planet.view.utils.loadImage

class adapter_for_item_of_hour(val list_of_hour: List<Temp_with_image>, val context: Context) :
    RecyclerView.Adapter<adapter_for_item_of_hour.viewHolder>() {


    class viewHolder(var view: HourItemForRecyclerviewBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<HourItemForRecyclerviewBinding>(
            inflater,
            R.layout.hour_item_for_recyclerview,
            parent,
            false
        )
        return viewHolder(view)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        holder.view.datatemp =    list_of_hour[position]

        holder.view.iconOfWeather.loadImage(
            context = context,
            URL = list_of_hour[position].image
        )
    }

    override fun getItemCount(): Int {
        return list_of_hour.size
    }



}
