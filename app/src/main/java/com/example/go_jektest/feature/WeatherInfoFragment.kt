package com.example.go_jektest.feature

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import com.example.go_jektest.R
import com.example.go_jektest.base.BaseFragment
import com.example.go_jektest.base.BaseViewModel
import com.example.go_jektest.network.model.TempratureHelper
import kotlinx.android.synthetic.main.frgment_forecast.*
import java.text.DecimalFormat

class WeatherInfoFragment : BaseFragment(){

    private lateinit var mViewModel : WeatherInfoViewModel

    override fun provideBaseModel(): BaseViewModel = mViewModel

    override fun layoutId(): Int = R.layout.frgment_forecast

    private lateinit var adapter: WeatherForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(WeatherInfoViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
        setUpRecyclerView()
        mViewModel.fetchWeatherData("Bengaluru",4)
    }

    private fun setUpRecyclerView() {
        adapter = WeatherForecastAdapter(arrayListOf())
        rvData.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(activity!!, R.drawable.bg_divider_recycler)!!)
        rvData.addItemDecoration(dividerItemDecoration)
        rvData.adapter = adapter
    }

    private fun observeData() {
        mViewModel.getWeatherInfo().observe(this, Observer {
            it?.let {
                val decimalFormat = DecimalFormat("0.#")
                tvCurrentTemp.text = String.format(resources.getString(R.string.current_temp), decimalFormat.format(it.currentTemp))
                updateRecyclerView(it.listOfForecasts)
            }
        })
    }

    private fun updateRecyclerView(listOfForecasts: ArrayList<TempratureHelper>) {
        adapter.replaceAll(listOfForecasts)
        animateRecyclerView()
    }

    private fun animateRecyclerView() {
        if (activity != null) {
            val rotation = AnimationUtils.loadAnimation(activity, R.anim.bottom_up)
            rvData.startAnimation(rotation)
        }
    }

    companion object {
        fun newInstance() = WeatherInfoFragment()
    }
}