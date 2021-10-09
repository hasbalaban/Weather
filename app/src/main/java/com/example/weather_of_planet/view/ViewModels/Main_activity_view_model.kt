package com.example.weather_of_planet.view.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_of_planet.view.database.DatabaseService
import com.example.weather_of_planet.view.models.model_of_weather_of_7days.weather_model
import com.example.weather_of_planet.view.models.models_for_database.DayWeather
import com.example.weather_of_planet.view.models.models_for_database.Temp_with_image
import com.example.weather_of_planet.view.weather_service.weather_service
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Main_activity_view_model(context: Context) : ViewModel() {

    //we use compositeDisposable to add request
    var compositeDisposable = CompositeDisposable()

    // declared liveData DataOFWeather
    val DataOFWeather = MutableLiveData<weather_model>()

    // declared liveData Sonuc
    var Sonuc = MutableLiveData<Boolean>()

    // declared dao
    val dao = DatabaseService.invoke(context).Weather_Dao()

    // mutable live data hours
    val dataOfHours = MutableLiveData<List<Temp_with_image>>()

    // mutable live data days
    val dataOfDays = MutableLiveData<List<DayWeather>>()

    // get forecast city from Api and add mutableLiveData
    fun get_weather_forecast(city: String) {

        compositeDisposable.add(
            weather_service().getDataQueryOneCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<weather_model>() {
                    override fun onSuccess(t: weather_model) {
                        Log.i("sonuc", "başarılı")
                        DataOFWeather.value = t
                        Sonuc.value = true
                    }

                    override fun onError(e: Throwable) {
                        Log.i("sonuc", e.message.toString())
                        Sonuc.value = false
                    }


                })
        )

    }


    //---------------- hours----------------
    fun SaveHoursDatas(hours: List<Temp_with_image>) {
        CoroutineScope(Dispatchers.Default).launch {
            // before delete all hours
            dao.AddHoursToDatabase()
            // then save all hours
            dao.AddHoursToDatabase(*hours.toTypedArray())
        }
    }

    //get from database
    fun getHoursFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            dataOfHours.value = dao.getAllHoursFromDatabase()

        }
    }


    //---------------- days----------------
    fun SaveDaysDatas(days: List<DayWeather>) {
        CoroutineScope(Dispatchers.Default).launch {
            // before delete all hours
            dao.AddDaysToDatabase()
            // then save all hours
            dao.AddDaysToDatabase(*days.toTypedArray())
        }
    }
    //get from database
    fun getDaysFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            dataOfDays.value = dao.getAllDaysFromDatabase()

        }
    }


}