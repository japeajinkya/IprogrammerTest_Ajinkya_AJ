@file:Suppress("DEPRECATION")

package com.machinetest.ajinkya.dashboard.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.machinetest.ajinkya.R
import com.machinetest.ajinkya.dashboard.home.adapter.CityAdapter
import com.machinetest.ajinkya.dashboard.home.data.CityResponse
import com.machinetest.ajinkya.dashboard.home.data.Weather
import com.machinetest.ajinkya.dashboard.home.viewmodel.CityViewModel
import com.machinetest.ajinkya.databinding.FragmentCityBinding
import com.machinetest.ajinkya.other.utils.AppUtility

class CityFragment : Fragment() {

    var rootView: View? = null
    lateinit var viewModel: CityViewModel
    lateinit var binding: FragmentCityBinding
    var listData = ArrayList<Weather>()
    lateinit var mAdapter: CityAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        binding.lifecycleOwner = this
        binding.cityView = viewModel
        rootView = binding.root
        init()
        return rootView
    }

    private fun init() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getAllNewsData(query!!,"094aa776d64c50d5b9e9043edd4ffd00")
                newsDataObservable()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun initView(data: CityResponse?) {
        listData.clear()
        listData.addAll(data!!.weather!!)
        mAdapter = CityAdapter(context, data, arrayListOf())

        if(listData.isNotEmpty()){
            binding.rvAllNews.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            mAdapter.updateData(context,
                listData
            )
            binding.txtNoData.visibility = View.GONE
        }else{
            binding.txtNoData.visibility = View.VISIBLE
        }

    }

    private fun newsDataObservable() {
        viewModel.allNewsServerData().observe(requireActivity(), Observer { modelResponse ->
            try {
                if(modelResponse!!.isSuccessful){
                    if (modelResponse.body() != null) {
                        initView( modelResponse.body())
                    }else {
                        AppUtility.showSimpleSnackbarShort(requireActivity(),"Something went wrong!!")
                    }
                }else{
                    Toast.makeText(context,modelResponse.errorBody().toString(),Toast.LENGTH_LONG).show()
                }
                binding.llList.visibility = View.VISIBLE
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }
}