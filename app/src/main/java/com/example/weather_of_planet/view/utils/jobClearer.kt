package com.example.weather_of_planet.view.utils

import kotlinx.coroutines.Job

class JobClearer(val jobs :ArrayList<Job>) {

    fun clear (){
        for (i in jobs){
            i.cancel()
        }
    }
}