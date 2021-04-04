package com.machinetest.ajinkya.dashboard.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.machinetest.ajinkya.dashboard.home.data.CityResponse
import com.machinetest.ajinkya.other.api.ApiClient
import com.machinetest.ajinkya.other.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CityViewModel : ViewModel() {

    lateinit var newsMutableLiveData: MutableLiveData<Response<CityResponse>>

    fun allNewsServerData(): MutableLiveData<Response<CityResponse>> {
        newsMutableLiveData = MutableLiveData()
        return newsMutableLiveData
    }

    fun getAllNewsData(q: String, appid: String) {

        val apiService: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call : Call<CityResponse> = apiService.providerRegister(q, appid)
        call.enqueue(object : Callback<CityResponse> {
            override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {
                try {
                    Log.e("Success", Gson().toJson(response.body()))
                    this@CityViewModel.newsMutableLiveData.value = response
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<CityResponse>, t: Throwable) {
                Log.d("FAILURE", "Inside onFailure")
            }
        })
    }
}