package com.example.weather_of_planet.view.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


//Shared Manager initialize heere
class sharedPreferencesCreater(
    val context: Context
) {
    lateinit var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("com.example.weather", Context.MODE_PRIVATE)

    }

    fun getSharedPreferenceManager() = sharedPreferencesCreater(context)

    fun saveDataToSharedprefence(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }


    fun AddDataStringToSharedprefence(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getDataStringFromSharedprefence(key: String, value: String): String {
        Log.i("shared1", sharedPreferences.getString(key, value)!!)
        return sharedPreferences.getString(key, value)!!
    }

    fun AddDataBoolenToSharedprefence(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getDataBoolenFromSharedprefence(key: String, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, value)
    }


}