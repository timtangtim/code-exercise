package com.projectx.webreqestlib

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


open class SingleSourceRetrofitManager {
    protected var retrofit: Retrofit? = null
    private var serviceMapping: MutableMap<Any, Any> = mutableMapOf()

    companion object {
        var DEBUG = false
    }

    open fun getRetrofit(url: String): Retrofit {
        if (retrofit == null) {
            val clientBuilder = OkHttpClient.Builder()
            if (DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }
            retrofit = Retrofit.Builder().baseUrl(url).client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }

    private fun checkRetrofit() {
        if (retrofit != null) {
            throw Exception("retrofit not init")
        }
    }


}

fun <T> Single<T>?.call(observer: CusSO<T>): CusSO<T>? {
    return this?.call(null, observer)
}

fun <T> Single<T>?.call(observer: CusSO<T>, scheduler: Scheduler): CusSO<T>? {
    return this?.call(null, observer, scheduler)
}

fun <T> Single<T>?.call(retryWithDelay: RetryWithDelay? = null, observer: CusSO<T>, scheduler: Scheduler = Schedulers.io()): CusSO<T>? {
    val temp = this?.subscribeOn(scheduler)?.observeOn(AndroidSchedulers.mainThread())
    if (retryWithDelay != null) {
        return temp?.retryWhen(retryWithDelay)?.subscribeWith(observer)
    }
    return temp?.subscribeWith(observer)
}
