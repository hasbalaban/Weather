package com.example.weather_of_planet.view.models.model_of_weather_of_7days

data class Current (

	val last_updated_epoch : Int,
	val last_updated : String,
	val temp_c : Double,
	val temp_f : Double,
	val is_day : Int,
	val condition : Condition,
	val wind_mph : Double,
	val wind_kph : Double,
	val wind_degree : Int,
	val wind_dir : String,
	val pressure_mb : Int,
	val pressure_in : Double,
	val precip_mm : Double,
	val precip_in : Double,
	val humidity : Int,
	val cloud : Int,
	val feelslike_c : Double,
	val feelslike_f : Double,
	val vis_km : Int,
	val vis_miles : Int,
	val uv : Int,
	val gust_mph : Double,
	val gust_kph : Double,
	val air_quality : Air_quality
)