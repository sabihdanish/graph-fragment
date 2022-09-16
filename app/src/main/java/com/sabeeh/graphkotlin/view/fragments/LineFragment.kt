package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding
import com.sabeeh.graphkotlin.databinding.GraphLineBinding

class LineFragment: Fragment() {
    private lateinit var binding: GraphLineBinding
    private lateinit var lineChart: LineChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphLineBinding.inflate(inflater,container,false)

        lineChart = binding.lineChart
        val growthArray = Array<Int>(6) {0}
        for (i in 1..6) {
            growthArray[i-1] = i+21/i
        }
        populateLineChart(growthArray)

        return binding.root
    }


    private fun populateLineChart(values: Array<Int>) {
        val lineChartEntries: ArrayList<Entry> = ArrayList()

        var i = 0

        for (entry in values) {
            var value = values[i].toFloat()
            lineChartEntries.add(Entry(i.toFloat(), value))
            i++
        }
        val lineDataSet = LineDataSet(lineChartEntries, "")
        lineDataSet.setColors(*ColorTemplate.PASTEL_COLORS)
        val data = LineData(lineDataSet)
        lineChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = lineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        lineChart.legend.isEnabled = false

        //remove description label
        lineChart.description.isEnabled = false

        //add animation
        lineChart.animateX(1000, Easing.EaseInSine)
        lineChart.data = data
        //refresh
        lineChart.invalidate()
    }
    
}