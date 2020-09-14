package com.projectx.codeexercise.repo

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import com.projectx.codeexercise.request.WeatherApiManager
import com.projectx.codeexercise.request.WeatherApiService
import com.projectx.codeexercise.request.WeatherInfo
import com.projectx.webreqestlib.CusSO
import com.projectx.webreqestlib.call
import retrofit2.HttpException
import java.lang.Exception

class DataRepository(var apiHelper: WeatherApiManager) {
    companion object{
        const val TAG = "DataRepository"
    }
    private var getDataService =
        apiHelper?.getRetrofit("")?.create(WeatherApiService::class.java)

    fun getData(
        disposables: CompositeDisposable?,
        city: String,
        data: MutableLiveData<WeatherInfo>,
        errorCode: MutableLiveData<String>? = null,
        updateHis: (WeatherInfo) -> Unit
    ): MutableLiveData<WeatherInfo> {
        val request = getDataService?.getWeather(city)?.call(
            object : CusSO<WeatherInfo>() {
                override fun successHandler(t: WeatherInfo) {
                    Log.d(TAG, "onSuccess: ${t.timezone}")
                    data.value = t
                    updateHis(t)
                }

                override fun errorHandler(e: Throwable) {
                    Log.e(TAG, "onError:", e)
                    errorHandle(errorCode, e)
                }

            }
        )
        request?.let { disposables?.add(it) }
        return data
    }

    fun getDataWithGps(
        disposables: CompositeDisposable?,
        location: Location?,
        data: MutableLiveData<WeatherInfo>,
        errorCode: MutableLiveData<String>? = null,
        updateHis: (WeatherInfo) -> Unit
    ): MutableLiveData<WeatherInfo> {
        location?.also {
            val request = getDataService?.getWeatherWithGps(location.latitude.toString(), location.longitude.toString())?.call(
                object : CusSO<WeatherInfo>() {
                    override fun successHandler(t: WeatherInfo) {
                        Log.d(TAG, "onSuccess: ${t.timezone}")
                        data.value = t
                        updateHis(t)
                    }

                    override fun errorHandler(e: Throwable) {
                        Log.e(TAG, "onError:", e)
                        errorHandle(errorCode, e)
                    }

                }
            )
            request?.let { disposables?.add(it) }

        }
        return data
    }

    fun errorHandle(errorCode: MutableLiveData<String>?, e: Throwable) {
        try {
            errorCode?.value = (e as HttpException).code().toString()
        } catch (err: Exception) {
            Log.e(TAG, "onError:", err)
        }
    }
}
