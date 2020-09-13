package com.projectx.codeexercise

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectx.codeexercise.generated.callback.OnClickListener
import com.projectx.codeexercise.recycleview.RecentSearchAdapter

const val TAG = "BindingAdapter"

@BindingAdapter("timezone")
fun timezoneTransform(view: TextView, timezone: Int) {
    //    Log.d(TAG, "TimezoneTransform: $timezone")
    val timezone = calTimezone(timezone)
    when {
        timezone > 0 -> {
            view.text = "(GMT +$timezone)"
        }
        timezone == 0 -> {
            view.text = "(GMT)"
        }
        else -> {
            view.text = "(GMT $timezone)"
        }
    }
    //    Log.d(TAG, "TimezoneTransform: ${view.text}")
}

fun calTimezone(timezone: Int): Int {
    return timezone.div(60).div(60)
}

@BindingAdapter("celsius")
fun transformCelsius(view: TextView, celsius: Double) {
    view.text = "${calCelsius(celsius)}°C"
}

fun calCelsius(celsius: Double): Float {
    return celsius.div(10).toFloat()
}


@BindingAdapter("recentSearchAdapter", "layoutManager")
fun bindAdapter(view: RecyclerView, recentSearchAdapter: RecentSearchAdapter, layoutManager: LinearLayoutManager) {
    view.layoutManager = layoutManager
    view.adapter = recentSearchAdapter
}