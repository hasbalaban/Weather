package com.example.weather_of_planet.view.weather_service


import com.example.weather_of_planet.view.models.model_of_weather_of_7days.weather_model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface weather_requests_Dao_I {

    //7 günlük hava durumu
    @GET("v1/forecast.json?key=c0e3565ced6b4faf952144140212909&days=3&aqi=yes&alerts=yes")
    fun getForecastFromApi(@Query("q") city: String): Single<weather_model>


}