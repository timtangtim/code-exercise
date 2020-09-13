package com.projectx.codeexercise

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projectx.codeexercise.databinding.FragmentSearchBinding
import com.projectx.codeexercise.databinding.RecentSearchFragmentBinding
import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherApiManager

class RecentSearchFragment : Fragment() {

    companion object {
        fun newInstance() = RecentSearchFragment()
    }

    private lateinit var viewModel: RecentSearchViewModel
    private lateinit var binding: RecentSearchFragmentBinding
    private val apiHelper = WeatherApiManager("https://api.openweathermap.org")
    private val repo = DataRepository(apiHelper)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.recent_search_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().let {
            viewModel = ViewModelProvider.AndroidViewModelFactory(it.application).create(
                RecentSearchViewModel::class.java
            )
            viewModel.repo = repo
            binding.viewmodel = viewModel
            binding.lifecycleOwner = this

            viewModel.hisSet.observe(viewLifecycleOwner, Observer {
                binding.hisList.adapter?.notifyDataSetChanged()
            })
        }
    }

}