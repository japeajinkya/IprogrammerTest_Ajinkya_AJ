package com.machinetest.ajinkya.dashboard.home.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.machinetest.ajinkya.R
import com.machinetest.ajinkya.dashboard.home.data.CityResponse
import com.machinetest.ajinkya.dashboard.home.data.Weather
import kotlinx.android.synthetic.main.item_all_news.view.*
import java.util.*
import kotlin.collections.ArrayList

class CityAdapter(
    private var context: Context?,
    private var mCityResponse: CityResponse?,
    private var List: ArrayList<Weather>
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() , Filterable {

    var filterList = ArrayList<Weather>()

    fun updateData(context: Context?, data: ArrayList<Weather>) {
        this.context = context
        this.List.clear()
        this.List.addAll(data)
        this.filterList.clear()
        this.filterList = List
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_all_news, parent, false))

    override fun getItemCount() = filterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterList[position],mCityResponse!!)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val txtTemperatureName = view.txtTemperatureName
        private val txtTemperature = view.txtTemperature
        private val txtCity = view.txtCity
        private val txtDateTime = view.txtDateTime

        fun bind(data: Weather,mCityResponse: CityResponse) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txtCity.text = "City : " + mCityResponse.name
                txtDateTime.text = "Time Zone : " + mCityResponse.timezone

                txtTemperatureName.text = "Temperature : " + data.main
                txtTemperature.text ="Temperature Description: " + data.description
            } else {
                txtCity.text = "City : " + mCityResponse.name
                txtDateTime.text = "Time Zone : " + mCityResponse.timezone

                txtTemperatureName.text = "Temperature : " + data.main
                txtTemperature.text ="Temperature Description: " + data.description
            }
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val filterResults = FilterResults()
                val resultList = ArrayList<Weather>()
                filterList = if (charSearch.isEmpty()) {
                    List
                } else {
                    for (row in List) {
                        if (row.main!!.toLowerCase(Locale.getDefault()).contains(charSearch.toLowerCase(Locale.getDefault()))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                filterResults.values = filterList
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Weather>
                notifyDataSetChanged()
            }
        }
    }
}