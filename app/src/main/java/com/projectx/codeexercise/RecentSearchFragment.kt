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
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectx.codeexercise.databinding.FragmentSearchBinding
import com.projectx.codeexercise.databinding.RecentSearchFragmentBinding
import com.projectx.codeexercise.repo.DataRepository
import com.projectx.codeexercise.request.WeatherApiManager

class RecentSearchFragment : Fragment() {

    private lateinit var viewModel: RecentSearchViewModel
    private lateinit var binding: RecentSearchFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =  ViewModelProvider(this).get(RecentSearchViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.recent_search_fragment, container, false)
        binding.hisList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().let {
            binding.viewmodel = viewModel
            binding.lifecycleOwner = this

            viewModel.hisSet.observe(viewLifecycleOwner, Observer {
                binding.hisList.adapter?.notifyDataSetChanged()
            })
        }
    }

}