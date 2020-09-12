package com.projectx.codeexercise.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import com.projectx.codeexercise.request.WeatherApiManager
import com.projectx.codeexercise.request.WeatherApiService
import com.projectx.codeexercise.request.WeatherInfo
import com.projectx.webreqestlib.BaseResponse
import com.projectx.webreqestlib.CusSO
import com.projectx.webreqestlib.call

class DataRepository(var apiHelper: WeatherApiManager) {
    companion object{
        const val TAG = "DataRepository"
    }
    private var getDataService =
        apiHelper?.getRetrofit("")?.create(WeatherApiService::class.java)

    var data = MutableLiveData<WeatherInfo>();

    fun getData(disposables: CompositeDisposable?, city: String): MutableLiveData<WeatherInfo> {

        val request = getDataService?.getWeather(city)?.call(
            object : CusSO<BaseResponse<WeatherInfo>>() {
                override fun successHandler(t: BaseResponse<WeatherInfo>) {
                    Log.d(TAG, "onSuccess: ${t.toString()}")
                    data.value = t.data
                }

                override fun errorHandler(e: Throwable) {
                    Log.e(TAG, "onError:", e)
                }

            }
        )
        request?.let { disposables?.add(it) }
        return data
    }
}
