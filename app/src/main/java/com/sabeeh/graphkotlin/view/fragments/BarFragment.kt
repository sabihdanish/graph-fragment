package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.sabeeh.graphkotlin.databinding.GraphBarBinding
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding

class BarFragment: Fragment() {
    private lateinit var binding: GraphBarBinding
    private lateinit var barChart: BarChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphBarBinding.inflate(inflater,container,false)

        barChart = binding.barChart
        val growthArray = Array<Int>(6) {0}
        for (i in 1..6) {
            growthArray[i-1] = i+66/i
        }
        populateBarChart(growthArray)

        return binding.root
    }

    private fun populateBarChart(values: Array<Int>) {
        //adding values
        val ourBarEntries: ArrayList<BarEntry> = ArrayList()
        var i = 0

        for (entry in values) {
            var value = values[i].toFloat()
            ourBarEntries.add(BarEntry(i.toFloat(), value))
            i++
        }


        val barDataSet = BarDataSet(ourBarEntries, "")
        //set a template coloring
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        val data = BarData(barDataSet)
        barChart.data = data
        //setting the x-axis
        val xAxis: XAxis = barChart.xAxis
        //calling methods to hide x-axis gridlines
        barChart.axisLeft.setDrawGridLines(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove legend
        barChart.legend.isEnabled = false

        //remove description label
        barChart.description.isEnabled = false

        //add animation
        barChart.animateY(3000)
        //refresh the chart
        barChart.invalidate()
    }
    
    
}