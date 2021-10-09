package com.example.weather_of_planet.view.models.models_for_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day")
data class DayWeather(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "dayId")
    val dayId:Int=0,
    @ColumnInfo(name = "dayName")
    val dayName: String,
    @ColumnInfo(name = "humidity")
    val humidity :String,
    @ColumnInfo(name = "max_temp")
    val max_temp :String,
    @ColumnInfo(name = "min_temp")
    val min_temp :String,
    @ColumnInfo(name = "image")
    val image :String

)
