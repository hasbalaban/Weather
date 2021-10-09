package com.example.weather_of_planet.view.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.example.weather_of_planet.R
import com.example.weather_of_planet.databinding.ActivityWevviewBinding
import com.example.weather_of_planet.view.utils.sharedPreferencesCreater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Activity_wevview : AppCompatActivity() {
    private lateinit var dataBindingActivity_wevview: ActivityWevviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingActivity_wevview =
            DataBindingUtil.setContentView(this, R.layout.activity_wevview)



        CoroutineScope(Dispatchers.Main).launch {
            val url = intent.getStringExtra("url")
            Log.i("urlll", url.toString())
            Log.i("urlll222", url.toString())

            setup(url!!)
        }
    }

   fun setup(url:String) {

        val webView = dataBindingActivity_wevview.webViewOfDetailsOfCity

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.loadUrl(url)

}

    override fun onBackPressed() {
        val checkIsBack = dataBindingActivity_wevview.webViewOfDetailsOfCity.canGoBack()
        if (checkIsBack)
            dataBindingActivity_wevview.webViewOfDetailsOfCity.goBack()
        else
            super.onBackPressed()

    }
}