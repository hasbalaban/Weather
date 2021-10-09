package com.example.weather_of_planet.view.models


import com.example.weather_of_planet.view.models.model_of_weather_of_7days.Hour
import com.example.weather_of_planet.view.models.models_for_database.Temp_with_image

data class createNewModelForHour(val hour: Hour) {
    //i will explain this function as soon as possible
    fun CreateNewModel(): Temp {
        var time = hour.time.toString()
        val x = time.length
        val start = x - 5
        val end = x - 3
        time = time.subSequence(start, end).toString()
        val temp_c = hour.temp_c.toString() + "°"
        val humidity = hour.humidity.toString() + "%"
        return Temp(time, temp_c, humidity)
    }

    fun CreateNewModelWithImage(): Temp_with_image {
        var time = hour.time.toString()
        val x = time.length
        val start = x - 5
        val end = x - 3
        time = time.subSequence(start, end).toString()
        val temp_c = hour.temp_c.toString() + "°"
        val humidity = hour.humidity.toString() + "%"
        val image = "https:" + hour.condition.icon
        return Temp_with_image(time = time, temp_c = temp_c, humidity = humidity, image = image)
    }
}