package com.projectx.codeexercise

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projectx.codeexercise.databinding.FragmentSearchBinding
import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherApiManager


class SearchFragment : Fragment() {

    companion object {
        const val TAG = "SearchFragment"
    }

    private lateinit var mViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private val apiHelper = WeatherApiManager("https://api.openweathermap.org")
    private val repo = DataRepository(apiHelper)
    private val handler = Handler()
    private val runnable = Runnable {
        mViewModel.searchWeather()
    }
    private lateinit var sharedPreferences: SharedPreferencesObject
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().let {
            sharedPreferences = SharedPreferencesObject(it)

            //setup the history list
            adapter =
                ArrayAdapter<String>(it, android.R.layout.simple_dropdown_item_1line, mutableListOf<String>())

            binding.input.setAdapter(adapter)

            mViewModel = ViewModelProvider.AndroidViewModelFactory(it.application).create(
                SearchViewModel::class.java
            )

            mViewModel.repo = repo
            binding.viewmodel = mViewModel
            binding.lifecycleOwner = this
            sharedPreferences.getStringSet(KEY_SEARCH_HIS)?.let { mutableSet ->
                adapter.addAll(mutableSet)
                adapter.notifyDataSetChanged()
            }


            sharedPreferences.getString(KEY_RECENT_SEARCH, "")?.let { city ->
                mViewModel.cityString.value = city
            }

            //Ajax search
            mViewModel.cityString.observe(viewLifecycleOwner, Observer { cityString ->
                if (cityString.isEmpty()) {
                    return@Observer
                }
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            })

            //basic error handle
            //TODO: more detail message
            mViewModel.errorCode.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), getText(R.string.error_data_not_found), Toast.LENGTH_LONG).show()
            })


            mViewModel.data.observe(viewLifecycleOwner, Observer { weatherInfo ->
                val stringSet = sharedPreferences.getStringSet(KEY_SEARCH_HIS)
                if (stringSet == null) {
                    sharedPreferences.setStringSet(KEY_SEARCH_HIS, mutableSetOf(weatherInfo.name!!))
                } else {
                    stringSet.add(weatherInfo.name!!)
                    sharedPreferences.setStringSet(KEY_SEARCH_HIS, stringSet)
                }

                //update the search list
                adapter.remove(weatherInfo.name)
                adapter.add(weatherInfo.name)
                adapter.notifyDataSetChanged()

                //update recent search
                sharedPreferences.setString(KEY_RECENT_SEARCH, weatherInfo.name!!)
            })
        }
    }

}