package com.example.weather_of_planet.view.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cityId")
    val cityId : Int,
    @ColumnInfo(name = "cityName")
    val cityName: String

)
