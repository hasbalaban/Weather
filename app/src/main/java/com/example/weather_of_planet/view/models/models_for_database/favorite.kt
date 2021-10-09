package com.example.weather_of_planet.view.models.models_for_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favoriteId")
    val favoriteId: Int=0,
    @ColumnInfo(name = "FavoriteCityName")
    val FavoriteCityName: String


    /*,
    @ColumnInfo(name = "FavoriteCityDeggres")
    val FavoriteCityDeggres: String,
    @ColumnInfo (name = "favoriteImage")
    val favoriteImage: String


     */
)