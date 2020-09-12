package com.projectx.codeexercise.request

import com.projectx.webreqestlib.BaseResponse
import com.projectx.webreqestlib.SingleSourceRetrofitManager
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


class WeatherApiManager(var url: String) : SingleSourceRetrofitManager() {
    lateinit var deviceApiService: WeatherApiService

    init {
        DEBUG = true
        getRetrofit(url)
        createApiService()
    }

    /*
    for creating all api service
     */
    private fun createApiService() {
        deviceApiService = retrofit!!.create(WeatherApiService::class.java)
    }

}

interface WeatherApiService {

    @GET("data/2.5/weather?appid=95d190a434083879a6398aafd54d9e73")
    fun getWeather(@Query("q") city: String): Single<WeatherInfo>


    @GET("data/2.5/weather?appid=95d190a434083879a6398aafd54d9e73")
    fun getWeatherWithGps(@Query("lat") lat: String, @Query("lon") lon:String): Single<WeatherInfo>

}
