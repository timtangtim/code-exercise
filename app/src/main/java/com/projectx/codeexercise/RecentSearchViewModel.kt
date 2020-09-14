package com.projectx.codeexercise

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectx.codeexercise.recycleview.RecentSearchAdapter

class RecentSearchViewModel(application: Application) : SearchViewModel(application),
    View.OnClickListener {
    companion object {
        const val TAG = "SearchViewModel"
    }

    var sharedPreferences = SharedPreferencesObject(getApplication())
    var pendingToDelete = mutableSetOf<String>()
    var hisSet = MutableLiveData<MutableSet<String>>()

    val recentSearchAdapter = RecentSearchAdapter(hisSet).also { it.onClickListener = this  }.also { getHis() }
    val viewManager = LinearLayoutManager(getApplication())

    private fun getHis(): MutableLiveData<MutableSet<String>> {
        return hisSet.also { it.value = sharedPreferences.getStringSet(KEY_SEARCH_HIS) }
    }

    override fun onClick(v: View?) {
        if (v is AppCompatCheckBox) {
            if (v.isChecked) {
                pendingToDelete.add(v.tag as String)
            } else {
                pendingToDelete.remove(v.tag as String)
            }
        } else if (v is TextView) {
            Log.d(TAG, "onClick: IN")
            repo?.getData(disposable, v.text.toString(), data, errorCode, updateHis)
        }
    }

    fun deleteHis() {
        //skip if size = 0
        if (pendingToDelete.size == 0) {
            return
        }

        val set = sharedPreferences.getStringSet(KEY_SEARCH_HIS)
        set?.removeAll(pendingToDelete)?.also {

            if (it) hisSet.value = set

            if (pendingToDelete.contains(sharedPreferences.getString(KEY_RECENT_SEARCH,"")) && set.size > 0) {
                sharedPreferences.setString(KEY_RECENT_SEARCH, set.first())
            } else if (set.size == 0){
                //clear the recent search
                sharedPreferences.setString(KEY_RECENT_SEARCH, null)
            }
            //clear all
            sharedPreferences.setStringSet(KEY_SEARCH_HIS, null)
            //put new string set
            sharedPreferences.setStringSet(KEY_SEARCH_HIS, set)
            pendingToDelete.clear()
        }
    }
}