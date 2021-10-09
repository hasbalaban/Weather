package com.example.weather_of_planet.view.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class getCurrentTime() {

    fun Hour(): Int {
        // this blook for get temp-hour - humidity etc. from current time to after 6 hours
        val SimpleDateFormat = SimpleDateFormat("HH")
        val FromThisHour = SimpleDateFormat.format(Date()).toInt()
        return FromThisHour

    }

    fun Day(): Int {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)

        return day
    }
}