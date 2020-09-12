package com.projectx.codeexercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val coroutineScope = CoroutineScope(Dispatchers.Main + Job() + errorHandler)
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        coroutineScope.cancel()
        disposable.dispose()
        disposable.clear()
        super.onCleared()
    }

    companion object {
        val errorHandler = CoroutineExceptionHandler { _, error ->
            error.printStackTrace()
        }
    }
}