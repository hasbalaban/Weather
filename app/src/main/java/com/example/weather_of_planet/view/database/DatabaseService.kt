package com.example.weather_of_planet.view.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import com.example.weather_of_planet.view.models.models_for_database.Temp_with_image
import com.example.weather_of_planet.view.models.models_for_database.DayWeather

//create database and return to use
@Database(entities = arrayOf(Favorite::class,Temp_with_image::class,DayWeather::class),
    exportSchema = false, version = 2)
abstract class DatabaseService : RoomDatabase() {

    abstract fun Weather_Dao(): Weather_Dao_I

    companion object {

        @Volatile
        private var instance: DatabaseService? = null
        val Lock = Any()


          fun invoke(context: Context) = instance ?: synchronized(Lock) {

            instance ?: makeDatabase(context).also {
                instance = it
            }

        }


        fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseService::class.java,
            "WeatherDatabase"
        ).build()
    }


}