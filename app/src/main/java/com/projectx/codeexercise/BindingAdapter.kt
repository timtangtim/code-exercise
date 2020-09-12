package com.projectx.codeexercise

import android.widget.TextView
import androidx.databinding.BindingAdapter

const val TAG = "BindingAdapter"

@BindingAdapter("timezone")
fun timezoneTransform(view: TextView, timezone: Int) {
//    Log.d(TAG, "TimezoneTransform: $timezone")
    view.text = calTimezone(timezone).toString ()
//    Log.d(TAG, "TimezoneTransform: ${view.text}")
}

fun calTimezone(timezone: Int): Int {
    return (timezone / (60 * 60))
}