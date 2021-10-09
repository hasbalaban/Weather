package com.example.weather_of_planet.view.views


import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ActivitySettingsBinding
import com.example.weather_of_planet.view.utils.Intents
import com.example.weather_of_planet.view.utils.ModeControl
import com.example.weather_of_planet.view.utils.sharedPreferencesCreater

class settings : AppCompatActivity() {

    private lateinit var dataBindinSettings: ActivitySettingsBinding
    private lateinit var sharedManager: sharedPreferencesCreater

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ModeControl().setThema(this))
        super.onCreate(savedInstanceState)
        dataBindinSettings = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        sharedManager = sharedPreferencesCreater(this)
        val mode = sharedManager.getDataBoolenFromSharedprefence("mode", false)
        dataBindinSettings.Mode.isChecked = mode

        modeManager()
        clickMyAccout()


    }

    fun modeManager() {
        dataBindinSettings.Mode.setOnClickListener {

            val currentMode = dataBindinSettings.Mode.isChecked
            saveModeState(currentMode)


        }
    }


    fun saveModeState(mode: Boolean) {
        if (mode) {
            dataBindinSettings.Mode.isChecked = true
            sharedManager.AddDataBoolenToSharedprefence("mode", true)
            finish()
            Intents().runIntent(this, settings::class.java)


        } else {
            dataBindinSettings.Mode.isChecked = false
            sharedManager.AddDataBoolenToSharedprefence("mode", false)
            Intents().runIntent(this, settings::class.java)
            finish()


        }

    }

    fun clickMyAccout(){
        dataBindinSettings.MyAccount.setOnClickListener {
            val url ="https://www.twitter.com/hesenblbn101".toLowerCase()
            Intents().runIntentToWebCiew(this,Activity_wevview::class.java,url)
        }
    }


    override fun onBackPressed() {
        finish()
        //Intents().runIntent(this, MainActivity::class.java)
        super.onBackPressed()
    }


}