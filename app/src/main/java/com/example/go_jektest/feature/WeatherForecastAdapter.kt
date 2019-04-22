package com.example.go_jektest.feature

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.go_jektest.R
import com.example.go_jektest.network.model.TempratureHelper

class WeatherForecastAdapter(val list: MutableList<TempratureHelper> = arrayListOf()) :RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return ViewHolder(inflater.inflate(R.layout.item_forecast,p0,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.avgTemp.text = "${list[holder.adapterPosition].temp} C"
        holder.dayOfWeek.text = list[holder.adapterPosition].day
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dayOfWeek : TextView = itemView.findViewById(R.id.tvDay)
        val avgTemp : TextView = itemView.findViewById(R.id.tvTemp)
    }

    fun replaceAll(list:MutableList<TempratureHelper>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}