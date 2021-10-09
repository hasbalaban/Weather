package com.example.weather_of_planet.view.adapters

import android.content.Context
import android.view.*
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ItemForFavoriteBinding
import com.example.weather_of_planet.view.database.DatabaseService
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import com.example.weather_of_planet.view.utils.Intents
import com.example.weather_of_planet.view.utils.NoImage
import com.example.weather_of_planet.view.utils.sharedPreferencesCreater
import com.example.weather_of_planet.view.views.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// adater for favorite city that we saved
class adapter_for_item_of_favorite(var list_of_favorites: List<Favorite>, val context: Context) :
    RecyclerView.Adapter<adapter_for_item_of_favorite.ViewHolder>() {

    val dao = DatabaseService.invoke(context).Weather_Dao()

    class ViewHolder(val view: ItemForFavoriteBinding) : RecyclerView.ViewHolder(view.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var LayoutInflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<ItemForFavoriteBinding>(
            LayoutInflater,
            R.layout.item_for_favorite,
            parent,
            false
        )
        return ViewHolder(view)
    }

    //bind here - and do click operation here
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.view.favorite = list_of_favorites[position]
        clicksOperation(holder, list_of_favorites[position], position)

        //  holder.view.weatherStateIcon.NoImage()

    }

    override fun getItemCount(): Int {
        return list_of_favorites.size
    }


    // click operation do here
    fun clicksOperation(holder: ViewHolder, list_of_favorites: Favorite, position: Int) {
        holder.view.sil.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                val favoriteId = list_of_favorites.favoriteId
                dao.delete_One_Favorite_By_Id(favoriteId)
                delete(position)
                Intents().runIntent(context, MainActivity::class.java)

            }

        }

        holder.view.FavoriteCity.setOnClickListener {
            val defaultCity = list_of_favorites.FavoriteCityName
            sharedPreferencesCreater(context).AddDataStringToSharedprefence(
                "defaultCity",
                defaultCity
            )
            Intents().runIntent(context, MainActivity::class.java)

        }


    }

    // delete city from favorite city that we want dete
    // and set new defaultCity for start
    fun delete(position: Int) {
        val sharedManager = sharedPreferencesCreater(context)
        val defaultCity = sharedManager.getDataStringFromSharedprefence(
            "defaultCity", "Ankara"
        )
        if (defaultCity == list_of_favorites[position].FavoriteCityName) {


            if (list_of_favorites.size > 1) {
                if (list_of_favorites.size - 1 != position) {

                    sharedManager.AddDataStringToSharedprefence(
                        "defaultCity",
                        list_of_favorites[0].FavoriteCityName
                    )

                } else {
                    sharedManager.AddDataStringToSharedprefence("defaultCity", "Ankara")
                }
            } else {
                sharedManager.AddDataStringToSharedprefence("defaultCity", "Ankara")
            }

        }


    }
}