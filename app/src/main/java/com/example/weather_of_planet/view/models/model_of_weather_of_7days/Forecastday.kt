package com.example.weather_of_planet.view.models.model_of_weather_of_7days


data class Forecastday (

	val date : String,
	val date_epoch : Int,
	val day : Day,
	val astro : Astro,
	val hour : List<Hour>
)