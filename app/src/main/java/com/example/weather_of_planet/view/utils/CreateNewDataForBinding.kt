package com.example.weather_of_planet.view.utils

import com.example.weather_of_planet.view.enums.enum_for_days
import com.example.weather_of_planet.view.models.model_of_weather_of_7days.Forecastday
import com.example.weather_of_planet.view.models.models_for_database.DayWeather

// this class create new model to use
class CreateNewDataForBinding {

    fun createNewModelForBinding(
        dayName: Int,
        data: Forecastday
    ): DayWeather {


        val dayName = when (dayName) {
            1 -> enum_for_days.Sunday.name.toString()
            2 -> enum_for_days.Monday.name.toString()
            3 -> enum_for_days.Thursday.name.toString()
            4 -> enum_for_days.Wednesday.name.toString()
            5 -> enum_for_days.Tuesday.name.toString()
            6 -> enum_for_days.Friday.name.toString()
            7 -> enum_for_days.Saturday.name.toString()
            else -> "veri alınırken hata"

        }
        val dayHumidity = data.day.avghumidity.toString() + " %"
        val day_max_temp = data.day.maxtemp_c.toString() + " °"
        val day_min_temp = data.day.mintemp_c.toString() + " °"
        val image = "https:"+data.day.condition.icon
        val new = DayWeather(
            dayName = dayName, humidity = dayHumidity, max_temp = day_max_temp,
            min_temp = day_min_temp, image = image
        )
        return new


    }
}