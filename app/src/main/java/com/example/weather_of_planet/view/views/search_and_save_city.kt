package com.example.weather_of_planet.view.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ActivitySearchAndSaveCityBinding
import com.example.weather_of_planet.view.ViewModels.Search_and_Save_View_Model
import com.example.weather_of_planet.view.adapters.adapter_for_city_search
import com.example.weather_of_planet.view.interfaces.Clickslistener
import com.example.weather_of_planet.view.models.models_for_database.Favorite
import com.example.weather_of_planet.view.utils.Intents
import com.example.weather_of_planet.view.utils.ModeControl
import kotlinx.coroutines.*

class search_and_save_city : AppCompatActivity(), TextWatcher, Clickslistener {

    private lateinit var Search_and_Save_View_Model: Search_and_Save_View_Model
    private lateinit var dataBindingSearchAndSave: ActivitySearchAndSaveCityBinding
    private lateinit var ListCities: List<Favorite>
    // when you write city  name - create new recyclerview - before you set to recyclerview
    // if you write another name cancel last job
    var CompletableJob = Job()
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        //set theme
        setTheme(ModeControl().setThema(this))
        super.onCreate(savedInstanceState)


        val layout = R.layout.activity_search_and_save_city
        dataBindingSearchAndSave = DataBindingUtil.setContentView(this, layout)

        settings()
        setup()

    }
    // initialize
    fun settings() {
        dataBindingSearchAndSave.cityName.addTextChangedListener(this)

        Search_and_Save_View_Model = Search_and_Save_View_Model()
        Search_and_Save_View_Model.getCitiesFromText(this)
    }

    //observe favorites
    fun setup() {
        Search_and_Save_View_Model.MutableCities.observe(this, Observer {
            ListCities = it

        })

    }

    fun SetAddLastSearch() {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    // observe edittext - and cancel last job here
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (dataBindingSearchAndSave.cityName.text.toString() != "") {
            dataBindingSearchAndSave.clear.visibility = View.VISIBLE
            dataBindingSearchAndSave.recyclerViewForCity.visibility = View.VISIBLE
        } else {
            dataBindingSearchAndSave.clear.visibility = View.INVISIBLE
            dataBindingSearchAndSave.recyclerViewForCity.visibility = View.INVISIBLE
        }
        if (job != null) {
            job!!.cancel()
            Log.i("iptall", "iptal edildi")
        }


    }


    // add new job and create new recyclerview adapter
    override fun afterTextChanged(s: Editable?) {
        var text = dataBindingSearchAndSave.cityName.text.toString()
        text = text.capitalize()


        job = CoroutineScope(CompletableJob + Dispatchers.IO).launch {

        }

        SetDataToRecyclerView(text)
    }

    fun SetDataToRecyclerView(text: String) {
        Search_and_Save_View_Model.MutableYukleme.observe(this, Observer {
            if (it) {

                val liste = Search_and_Save_View_Model.SetDataToRecyclerView(text)
                setAdapterToCityRecyclerView(liste)

            }
        })


    }

    // add city to recyclerview that equals to you search it
    fun setAdapterToCityRecyclerView(list: List<Favorite>) {
        val adapter = adapter_for_city_search(list, this)
        dataBindingSearchAndSave.recyclerViewForCity.layoutManager = LinearLayoutManager(this)
        dataBindingSearchAndSave.recyclerViewForCity.adapter = adapter

    }

    //listen click here
    override fun clickListener(view: View) {

        when (view.id) {
            dataBindingSearchAndSave.clear.id -> dataBindingSearchAndSave.cityName.setText("")
            dataBindingSearchAndSave.back.id -> {
                Intents().runIntent(this@search_and_save_city, MainActivity::class.java)
            }

        }

    }

    override fun onStop() {
        finish()
        super.onStop()
    }



}
