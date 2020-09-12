package com.projectx.codeexercise

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherInfo


class SearchViewModel(application: Application) : BaseViewModel(application) {

    companion object {
        const val TAG = "SearchViewModel"
    }
    var repo: DataRepository? = null
    var data = MutableLiveData<WeatherInfo>() // weather info
    var cityString = MutableLiveData<String>() // user input
    var errorCode = MutableLiveData<String>() // http error code

    fun searchWeather() {
        repo?.getData(disposable, cityString.value.toString(), data, errorCode)
    }

    @SuppressLint("MissingPermission")
    fun searchWeatherWithGps() {
        Log.d(TAG, "searchWeatherWithGps: IN")

        val mLocationManager =
            getApplication<Application>().getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        if (!setupPermissions()) {
            return
        }

        val locationGPS: Location? =
            mLocationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        val locationNet = mLocationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        var location = locationGPS ?: locationNet
        Log.d(TAG, "searchWeatherWithGps: ${location.toString()}")
        repo?.getDataWithGps(disposable, location, data, errorCode)
    }

    private fun setupPermissions(): Boolean {
        val permission = ContextCompat.checkSelfPermission(getApplication(),
            Manifest.permission.ACCESS_FINE_LOCATION)

        val permission2 = ContextCompat.checkSelfPermission(getApplication(),
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED || permission2 != PackageManager.PERMISSION_GRANTED) {
            Log.i(MainActivity.TAG, "Permission to record denied")
            Log.i(MainActivity.TAG, "Permission to record denied")
            return false
        }

        return true
    }
}