package com.example.revision

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.revision.api.Constant
import com.example.revision.api.NetworkResponse
import com.example.revision.api.RetrofitInstance
import com.example.revision.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewmodel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String){
        Log.i("City name :",city)

        viewModelScope.launch {
           val response = weatherApi.getWeather(Constant.apikey, city)
            if(response.isSuccessful){
                Log.i("Response :", response.body().toString())
                response.body()?.let {
                    _weatherResult.value = NetworkResponse.Success(it)
                }
            }else{
                Log.i("Error: ", response.message())
            }

        }
    }
}