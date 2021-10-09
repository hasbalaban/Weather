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
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception

class Search_and_Save_View_Model() : ViewModel() {
    val MutableCities = MutableLiveData<List<Favorite>>()
    val MutableYukleme = MutableLiveData<Boolean>()


    fun getCitiesFromText(context: Context) {

        MutableYukleme.value = false

        var collectChar = ""
        val cities = arrayListOf<Favorite>()


        val inputStream: InputStream = context.assets.open("ffffff.txt")
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val data = inputStream.bufferedReader().use { it.readText() }
                val textData = StringBuffer(data)
                var cityId: Int = 0

                for (i in textData.toString()) {
                    if (i.toString() == ",") {
                        cities.add(Favorite(cityId, collectChar))
                        collectChar = ""
                        cityId++

                    } else
                        collectChar = collectChar + i
                }

                withContext(Dispatchers.Main) {
                    MutableYukleme.value = true
                    MutableCities.value = cities

                }

            } catch (e: Exception) {
                e.printStackTrace()

            }


        }

    }


    fun SetDataToRecyclerView(text: String): List<Favorite> {

        val list = arrayListOf<Favorite>()
        val length = text.length
        // Log.i("texte", text)

        if (text != "" && text != " " && text.length > 1) {

            CoroutineScope(Dispatchers.IO).launch {
                if (MutableCities.value != null) {
                    for (i in MutableCities.value!!) {
                        if (i.FavoriteCityName.length >= length) {
                            if ((i.FavoriteCityName.subSequence(0, length)).toString() == text) {
                                list.add(i)

                            }
                        }
                    }

                }
            }

        }
        return list
    }


    fun Add_One_Favorite(favorite: Favorite,context: Context) {

        CoroutineScope(Dispatchers.Default).launch {
            val dao = DatabaseService.invoke(context).Weather_Dao()
            dao.Add_One_Favorite(favorite)
        }
    }


}


