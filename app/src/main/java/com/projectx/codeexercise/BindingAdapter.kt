package com.projectx.codeexercise

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

const val TAG = "BindingAdapter"

@BindingAdapter("timezone")
fun timezoneTransform(view: TextView, timezone:Int) {
//    Log.d(TAG, "TimezoneTransform: $timezone")
    view.text = (timezone / (60 * 60)).toString()
//    Log.d(TAG, "TimezoneTransform: ${view.text}")
}