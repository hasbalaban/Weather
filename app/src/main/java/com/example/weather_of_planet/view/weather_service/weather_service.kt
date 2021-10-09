package com.example.weather_of_planet.view.weather_service


import com.example.weather_of_planet.view.models.model_of_weather_of_7days.weather_model
import io.reactivex.Single

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class weather_service() {


    private var Base_Url = "https://api.weatherapi.com/"

    private val Retrofit = retrofit2.Retrofit.Builder().baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(weather_requests_Dao_I::class.java)



    fun getDataQueryOneCity(city:String): Single<weather_model> {

        return Retrofit.getForecastFromApi(city)

    }






}