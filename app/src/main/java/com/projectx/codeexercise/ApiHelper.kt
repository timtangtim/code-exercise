package com.projectx.codeexercise

import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherApiManager

class ApiHelper {
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ApiHelper? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ApiHelper().also { instance = it }
            }

    }

    private val apiManager = WeatherApiManager("https://api.openweathermap.org")
    val repo = DataRepository(apiManager)

}