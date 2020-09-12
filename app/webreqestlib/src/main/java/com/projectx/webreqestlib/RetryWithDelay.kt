package com.projectx.webreqestlib

import android.util.Log
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import io.reactivex.functions.Function
import retrofit2.HttpException

open class RetryWithDelay(private val maxRetries: Int = 3, private val retryDelayMillis: Long = 3000) :
    Function<Flowable<Throwable>, Flowable<*>> {

    companion object {
        const val TAG: String = "RetryWithDelay"
    }
    var retryCount: Int = 0

    override fun apply(t: Flowable<Throwable>): Flowable<*> {
        return t.flatMap(Function<Throwable, Flowable<*>> {
            if (it is HttpException) {
                Log.d(TAG, "this is http exception")
                when (it.code()) {
                    403, 502, 500 ->
                        return@Function Flowable.error<Any>(it)
                }
            }
            if (++retryCount <= maxRetries) {
                // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                Log.d(TAG, "get error, it will try after $retryDelayMillis millisecond, retry count $retryCount")
                Log.e(TAG, "getError: ${it.message}", it)
                return@Function Flowable.timer(retryDelayMillis, TimeUnit.MILLISECONDS)
            }
            // Max retries hit. Just pass the error along.
            Flowable.error<Any>(it)
        })
    }
}