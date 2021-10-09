package com.example.weather_of_planet.view.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import com.example.weather_of_planet.view.models.models_for_database.Temp_with_image
import com.example.weather_of_planet.view.models.models_for_database.DayWeather

@Dao
interface Weather_Dao_I {


    // --------------------- these function for Favorite ------------/////////
    @Query("select * from favorite")
    suspend fun getAllFavorites(): List<Favorite>

    @Insert
    suspend fun Add_One_Favorite(favorite: Favorite)

    @Query("delete from favorite where favoriteId=:favoriteId")
    suspend fun delete_One_Favorite_By_Id(favoriteId: Int)


    // --------------------- these function for Hours  ------------/////////
    @Query("delete from Temp_with_image")
    suspend fun AddHoursToDatabase()
    @Insert
    suspend fun AddHoursToDatabase(vararg hours: Temp_with_image)

    @Query ("select * from Temp_with_image")
    suspend fun getAllHoursFromDatabase ():List<Temp_with_image>



    // --------------------- these function for days  ------------/////////
    @Query("delete from day")
    suspend fun AddDaysToDatabase()

    @Insert
    suspend fun AddDaysToDatabase(vararg day: DayWeather)

    @Query ("select * from day")
    suspend fun getAllDaysFromDatabase ():List<DayWeather>






}