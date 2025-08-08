package com.example.revision

import android.util.Log
import androidx.lifecycle.ViewModel

class WeatherViewmodel: ViewModel() {

    fun getData(city: String){
        Log.i("City name :",city)
    }
}