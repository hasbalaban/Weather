package com.example.weather_of_planet.view.models.model_of_weather_of_7days


data class Location (

	val name : String,
	val region : String,
	val country : String,
	val lat : Double,
	val lon : Double,
	val tz_id : String,
	val localtime_epoch : Int,
	val localtime : String
)