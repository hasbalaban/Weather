package com.example.weather_of_planet.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ItemForSearchBinding
import com.example.weather_of_planet.view.views.MainActivity
import com.example.weather_of_planet.view.ViewModels.Search_and_Save_View_Model
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import com.example.weather_of_planet.view.utils.Intents
import com.example.weather_of_planet.view.utils.sharedPreferencesCreater

// adater for city that we search for
class adapter_for_city_search(val cities: List<Favorite>, val context: Context) :
    RecyclerView.Adapter<adapter_for_city_search.ViewHolder>() {


    class ViewHolder(val view: ItemForSearchBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapter_for_city_search.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<ItemForSearchBinding>(
            inflater,
            R.layout.item_for_search,
            parent,
            false
        )
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: adapter_for_city_search.ViewHolder, position: Int) {
        holder.view.city = cities[position]
        holder.view.cityName.setOnClickListener {

            //save this city that we click
            saveFavorite(cities[position])
        }

    }

    override fun getItemCount(): Int {
        return cities.size
    }

    //save function
    fun saveFavorite(favorite: Favorite) {
        val viewModel = Search_and_Save_View_Model()
        viewModel.Add_One_Favorite(favorite, context)

        val cityName = favorite.FavoriteCityName

        sharedPreferencesCreater(context).AddDataStringToSharedprefence ("defaultCity", cityName)
        Intents().runIntent(context, MainActivity::class.java)


    }
}