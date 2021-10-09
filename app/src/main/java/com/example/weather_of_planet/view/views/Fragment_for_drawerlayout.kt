package com.example.weather_of_planet.view.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager.*
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.FragmentForDrawerlayoutBinding
import com.example.weather_of_planet.view.ViewModels.Favorite_fragment_View_Model
import com.example.weather_of_planet.view.adapters.adapter_for_item_of_favorite
import com.example.weather_of_planet.view.interfaces.Clickslistener
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import com.example.weather_of_planet.view.utils.Intents


class fragment_for_drawerlayout : Fragment() {


    private lateinit var dataBindingFragmentDrawerLayout: FragmentForDrawerlayoutBinding
    private lateinit var Favorite_fragment_View_Model: Favorite_fragment_View_Model
    private lateinit var List_Of_Favorites: List<Favorite>
    private lateinit var adapter: adapter_for_item_of_favorite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        dataBindingFragmentDrawerLayout = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_for_drawerlayout, container, false
        )

        Favorite_fragment_View_Model = Favorite_fragment_View_Model(requireContext())
        return dataBindingFragmentDrawerLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setup()
        newNavigationItemSelecter()
        super.onViewCreated(view, savedInstanceState)
    }


    fun setup() {
        Favorite_fragment_View_Model.getFavorites()
        ObserversBox()


    }


    fun ObserversBox() {
        Favorite_fragment_View_Model.List_Of_Favorites.observe(viewLifecycleOwner, Observer {
            List_Of_Favorites = it

            intializeFavoriteRecyclerView(List_Of_Favorites)
        })

    }

    fun intializeFavoriteRecyclerView(List_Of_Favorites: List<Favorite>) {
        adapter = adapter_for_item_of_favorite(List_Of_Favorites, requireContext())
        dataBindingFragmentDrawerLayout.favoritesRecycler.layoutManager =
            LinearLayoutManager(requireContext())
        dataBindingFragmentDrawerLayout.favoritesRecycler.adapter = adapter


    }


    fun newNavigationItemSelecter() {
        dataBindingFragmentDrawerLayout.navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.Settings -> {
                    Intents().runIntent(requireContext(), settings::class.java)
                }
                R.id.AddFavorite -> {

                    Intents().runIntent(requireContext(), search_and_save_city::class.java)
                }

            }
            return@setNavigationItemSelectedListener true

        }

    }


}