package com.example.weather_of_planet.view.views

import android.app.Activity
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ActivityMainBinding
import com.example.weather_of_planet.view.adapters.adapter_for_item_of_hour
import com.example.weather_of_planet.view.ViewModels.Main_activity_view_model
import com.example.weather_of_planet.view.adapters.adapter_for_item_of_day
import com.example.weather_of_planet.view.listeners.clickListenerForMainActivity
import com.example.weather_of_planet.view.models.createNewModelForHour
import com.example.weather_of_planet.view.models.model_of_weather_of_7days.*
import com.example.weather_of_planet.view.models.models_for_database.Temp_with_image
import com.example.weather_of_planet.view.models.models_for_database.DayWeather
import com.example.weather_of_planet.view.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    // some public declares
    lateinit var toogle: ActionBarDrawerToggle

    lateinit var dataBindingMainActiviy: ActivityMainBinding
    lateinit var Main_activity_view_model: Main_activity_view_model
    lateinit var Data_of_weather_from_Api: weather_model
    lateinit var jobs: ArrayList<Job>

    //sharedpreferences
    private lateinit var SharedManager: sharedPreferencesCreater
    override fun onCreate(savedInstanceState: Bundle?) {

        //   application.setTheme(ModeControl().setThema(applicationContext))
        setTheme(ModeControl().setThema(this))
        super.onCreate(savedInstanceState)
        dataBindingMainActiviy = DataBindingUtil.setContentView(this, R.layout.activity_main)

        SharedManager = sharedPreferencesCreater(this)
        jobs = ArrayList<Job>()

        setup()
        settingsOfDrawerLayout()
        clickToogleNew()
        swipeReflesh()


    }

    override fun onRestart() {

        if (jobs.size > 0)
            JobClearer(jobs).clear()
        val job = CoroutineScope(Dispatchers.Main).launch {
            setup()
        }
        jobs.add(job)
        setTheme(ModeControl().setThema(this))

        super.onRestart()
    }


    // use this fun for function holder
    fun setup() {

        val DefaultCity = SharedManager.getDataStringFromSharedprefence("defaultCity", "Ankara")
        settingsOfViewModel(DefaultCity)
        loadingFromDatabase(DefaultCity)
        bind_Weather_datas()


    }


    fun swipeReflesh() {
        dataBindingMainActiviy.reflesh.setOnRefreshListener {
            if (jobs.size > 0)
                JobClearer(jobs).clear()
            val job = CoroutineScope(Dispatchers.Main).launch {
                setup()
            }
            jobs.add(job)

        }
    }

    fun loadingFromDatabase(city: String) {
        val check = SharedManager.getDataBoolenFromSharedprefence("data", false)
        if (check) {

            getAllDataOfHoursFromDatabase(city)
        }

    }


    //set  settings of drawerLayout
    fun settingsOfDrawerLayout() {
        val drawerlayout = dataBindingMainActiviy.BaseDrawerLayout
        toogle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()
    }

    // initialize viewModel and start fun that we need
    fun settingsOfViewModel(cityName: String) {
        Main_activity_view_model = Main_activity_view_model(this)
        Main_activity_view_model.get_weather_forecast(cityName)

    }

    //this fun for toogle that we created for open the drawerlayout
    fun clickToogleNew() {
        dataBindingMainActiviy.newToogle.setOnClickListener {
            dataBindingMainActiviy.BaseDrawerLayout.open()
            Toast.makeText(this, "açıldı", Toast.LENGTH_SHORT).show()
        }
    }


    //observe here DAtaOfWeather then call ResultTrue() function
    fun bind_Weather_datas() {


        // observe DataOFWeather in ViewModel
        Main_activity_view_model.DataOFWeather.observe(this, Observer { weather_model ->
            Data_of_weather_from_Api = weather_model
            ResultTrue()
            dataBindingMainActiviy.reflesh.isRefreshing = false

        })


    }

    fun ResultTrue() {
//get current our use for 6 hour time - from that i created function
        val fromHour = getCurrentTime().Hour()
        var toHour = 24
        if (fromHour <= 17)
            toHour = fromHour + 6


        Log.i("size", Data_of_weather_from_Api.forecast.forecastday[0].hour.size.toString())
        //setting RecyclerView Hour
        val subListOfHour = Data_of_weather_from_Api.forecast.forecastday[0].hour
            .subList(fromHour, toHour)

        // we convert data that it come us to new model that we ability to use *
        val newDataForHour = arrayListOf<Temp_with_image>()
        for (position in subListOfHour) {
            newDataForHour.add(createNewModelForHour(position).CreateNewModelWithImage())
        }


        RecyclerViewAdapterForHor(newDataForHour)
        // send hours to save to saveHourDatasInDatabase function


        //setting RecyclerView Day
        val dataAsDay = Data_of_weather_from_Api.forecast.forecastday
        val newDataForDay = converModelToDayModel(dataAsDay)
        RecyclerViewAdapterForDay(newDataForDay)

        //save data to database
        saveDatasToDatabase(newDataForHour, newDataForDay)


        // set Local cityName from api- by use databinding
        dataBindingMainActiviy.cityName = Data_of_weather_from_Api.location

        // set sunset,sunrise and alert messages from api- by use databinding
        dataBindingMainActiviy.today = Data_of_weather_from_Api.forecast.forecastday[0]

        visibilities()

    }

    fun converModelToDayModel(dataAsDay: List<Forecastday>): List<DayWeather> {

        // we convert data that it come us to new model that we ability to use *
        val newDataForDay = arrayListOf<DayWeather>()
        var day = getCurrentTime().Day()
        var index = 0
        for (data in dataAsDay) {
            newDataForDay.add(CreateNewDataForBinding().createNewModelForBinding(day, data))
            if (day == 7)
                day = 1
            else
                day += 1
            index++
        }
        return newDataForDay
    }


    // fun for settings of recyclerview of hour do here
    fun RecyclerViewAdapterForHor(newDataForHour: List<Temp_with_image>) {
        // set hour adapter here
        val adapter = adapter_for_item_of_hour(newDataForHour, applicationContext)
        dataBindingMainActiviy.recyclerViewForHour.layoutManager =
            GridLayoutManager(
                applicationContext,
                newDataForHour.size,
                RecyclerView.VERTICAL,
                true
            )
        dataBindingMainActiviy.recyclerViewForHour.adapter = adapter

    }

    // fun for settings of recyclerview of day do here
    fun RecyclerViewAdapterForDay(dataAsDay: List<DayWeather>) {
        // set hour adapter here
        Log.i("eleman", dataAsDay.size.toString())
        val adapter = adapter_for_item_of_day(dataAsDay, applicationContext)
        dataBindingMainActiviy.recyclerViewForDay.layoutManager =
            LinearLayoutManager(applicationContext)
        dataBindingMainActiviy.recyclerViewForDay.adapter = adapter
    }


    fun saveDatasToDatabase(subListOfHour: List<Temp_with_image>, listOfDay: List<DayWeather>) {

        Main_activity_view_model.SaveHoursDatas(subListOfHour)
        Main_activity_view_model.SaveDaysDatas(listOfDay)
        SharedManager.AddDataBoolenToSharedprefence("data", true)
    }


    fun getAllDataOfHoursFromDatabase(city: String) {
        Main_activity_view_model.getHoursFromDatabase()
        Main_activity_view_model.getDaysFromDatabase()


        Main_activity_view_model.dataOfHours.observe(this, Observer {
            RecyclerViewAdapterForHor(it)


        })

        Main_activity_view_model.dataOfDays.observe(this, Observer {
            RecyclerViewAdapterForDay(it)


        })

        dataBindingMainActiviy.nameOfLocation.text = city

    }

    fun visibilities() {
        dataBindingMainActiviy.view3.visibility = View.VISIBLE
        dataBindingMainActiviy.view2.visibility = View.VISIBLE
        dataBindingMainActiviy.alerts.visibility = View.VISIBLE
        dataBindingMainActiviy.layoutOfSun.visibility = View.VISIBLE

    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }


}


/*
fun c (){
    dataBindingMainActiviy.navView.set {
        when (it.itemId){

            R.id.AddFavorite ->  Log.i("itemitem","favorite")
            R.id.Settings ->  Log.i("itemitem","Settings")
        }
        return@setNavigationItemSelectedListener true
    }
}



 */
// for test
/*
fun a( ) {
 val a= Model_for_item_hour("10","10","da","55")
 val b= Model_for_item_hour("140","140","fvc","255")
 val c = arrayListOf<Model_for_item_hour>(a,b,a,b,a,b)
 val adapter = adapter_for_item_of_hour(c, applicationContext)
 dataBindingMainActiviy.recyclerViewForHour.layoutManager =
     GridLayoutManager(applicationContext,c.size, RecyclerView.VERTICAL,true)
 dataBindingMainActiviy.recyclerViewForHour.adapter = adapter
}

*/

