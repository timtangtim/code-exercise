package com.projectx.codeexercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projectx.codeexercise.databinding.FragmentSearchBinding
import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherApiManager
import io.reactivex.disposables.CompositeDisposable


class SearchFragment : Fragment() {

    companion object {
        const val TAG = "SearchFragment"
    }

    private lateinit var mViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private val apiHelper = WeatherApiManager("https://api.openweathermap.org")
    private val repo = DataRepository(apiHelper)

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
            mViewModel = ViewModelProvider.AndroidViewModelFactory(it.application).create(
                SearchViewModel::class.java
            )

            mViewModel.repo = repo
            binding.viewmodel = mViewModel
            binding.lifecycleOwner = this

            val COUNTRIES = arrayOf(
                "Belgium", "France", "Italy", "Germany", "Spain"
            )

            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(it, android.R.layout.simple_dropdown_item_1line, COUNTRIES)
            binding.input.setAdapter(adapter)

            mViewModel.searchWeatherWithGps()
//            mViewModel.searchWeather()
        }
    }

}