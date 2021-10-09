package com.example.weather_of_planet.view.utils

import kotlinx.coroutines.Job

//clear all jobs
class JobClearer(val jobs :ArrayList<Job>) {

    fun clear (){
        for (i in jobs){
            i.cancel()
        }
    }
}