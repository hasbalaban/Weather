package com.example.weather_of_planet.view.models.models_for_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Temp_with_image")
data class Temp_with_image(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hour_id")
    val hour_id:Int =0,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "temp_c")
    val temp_c: String,
    @ColumnInfo(name = "humidity")
    val humidity: String,
    @ColumnInfo(name = "image")
    val image: String
)