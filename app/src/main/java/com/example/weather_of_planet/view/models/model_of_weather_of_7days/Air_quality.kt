package com.example.weather_of_planet.view.models.model_of_weather_of_7days

data class Air_quality (

	val co : Double,
	val no2 : Double,
	val o3 : Double,
	val so2 : Double,
	val pm2_5 : Double,
	val pm10 : Double,
	val us_epa_index : Int,
	val gb_defra_index : Int
)