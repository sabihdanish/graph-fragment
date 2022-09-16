package com.sabeeh.graphkotlin.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding
import com.sabeeh.graphkotlin.databinding.GraphPieBinding

class PieFragment: Fragment() {
    private lateinit var binding: GraphPieBinding
    private lateinit var pieChart: PieChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphPieBinding.inflate(inflater,container,false)
        pieChart = binding.pieChart
        val growthArray = Array<Int>(6) {0}
        val nameArray = Array<String>(6){"nameo"}
        for (i in 1..6) {
            nameArray[i-1] = "name"+i
            growthArray[i-1] = i+21*i
        }
        populatePieChart(growthArray,nameArray)

        return binding.root
    }

    private fun populatePieChart(values: Array<Int>, labels: Array<String>) {
        //an array to store the pie slices entry
        val ourPieEntry = ArrayList<PieEntry>()
        var i = 0

        for (entry in values) {
            //converting to float
            var value = values[i].toFloat()
            var label = labels[i]
            //adding each value to the pieentry array
            ourPieEntry.add(PieEntry(value, label))
            i++
        }

        //assigning color to each slices
        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#0E2DEC"))
        pieShades.add(Color.parseColor("#B7520E"))
        pieShades.add(Color.parseColor("#5E6D4E"))
        pieShades.add(Color.parseColor("#DA1F12"))
        pieShades.add(Color.parseColor("#DA1278"))
        pieShades.add(Color.parseColor("#009900"))

        //add values to the pie dataset and passing them to the constructor
        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        //setting the slices divider width
        ourSet.sliceSpace = 1f

        //populating the colors and data
        ourSet.colors = pieShades
        pieChart.data = data
        //setting color and size of text
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(10f)

        //add an animation when rendering the pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        //disabling center hole
        pieChart.isDrawHoleEnabled = false
        //do not show description text
        pieChart.description.isEnabled = false
        //legend enabled and its various appearance settings
        pieChart.legend.isEnabled = true
        pieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        pieChart.legend.isWordWrapEnabled = true

        //dont show the text values on slices e.g Antelope, impala etc
        pieChart.setDrawEntryLabels(false)
        //refreshing the chart
        pieChart.invalidate()

    }
    
    
}