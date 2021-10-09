package com.example.weather_of_planet.view.models.model_of_weather_of_7days

data class weather_model (

	val location : Location,
	val current : Current,
	val forecast : Forecast,
	val alerts : Alerts
)