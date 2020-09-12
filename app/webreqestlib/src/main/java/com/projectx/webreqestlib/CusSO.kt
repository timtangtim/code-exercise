package com.projectx.webreqestlib

import io.reactivex.observers.DisposableSingleObserver

abstract class CusSO<T>() : DisposableSingleObserver<T>() {

    final override fun onStart() {
        super.onStart()
        showProgress()
    }

    final override fun onSuccess(t: T) {
        dismissProgress()
        successHandler(t)

    }

    final override fun onError(e: Throwable) {
        errorHandler(e)
    }

    abstract fun successHandler(t: T)
    abstract fun errorHandler(e: Throwable)

    open fun showProgress() {

    }

    open fun dismissProgress() {

    }
}