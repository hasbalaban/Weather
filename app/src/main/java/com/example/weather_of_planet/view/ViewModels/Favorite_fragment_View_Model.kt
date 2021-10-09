package com.example.weather_of_planet.view.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_of_planet.view.database.DatabaseService
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Favorite_fragment_View_Model(context: Context) : ViewModel() {

    var List_Of_Favorites = MutableLiveData<List<Favorite>>()
    val dao = DatabaseService.invoke(context).Weather_Dao()

    // get favorite city from database and add mutableLiveData
    fun getFavorites() {

        CoroutineScope(Dispatchers.Main).launch {

            List_Of_Favorites.value = dao.getAllFavorites()

            Log.i("addddd", List_Of_Favorites.value!!.size.toString())
        }
    }
/*
    fun Add_One_Favorite(favorite: Favorite) {

        CoroutineScope(Dispatchers.Default).launch {

            dao.Add_One_Favorite(favorite)
        }
    }


 */

}