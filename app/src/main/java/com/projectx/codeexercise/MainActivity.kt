package com.projectx.codeexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherApiManager
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private val apiHelper = WeatherApiManager("https://api.openweathermap.org")
    private val disposable: CompositeDisposable? = CompositeDisposable()
    private val repo = DataRepository(apiHelper)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = repo.getData(disposable, "Hong Kong")
    }
}