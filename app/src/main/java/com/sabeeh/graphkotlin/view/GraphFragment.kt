package com.sabeeh.graphkotlin.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.sabeeh.graphkotlin.databinding.GraphBarBinding
import com.sabeeh.graphkotlin.databinding.GraphLineBinding
import com.sabeeh.graphkotlin.databinding.GraphPieBinding

class GraphFragment:Fragment() {
    private lateinit var bindingBar:GraphBarBinding
    private lateinit var bindingLine:GraphLineBinding
    private lateinit var bindingPie:GraphPieBinding
    private lateinit var ourPieChart: PieChart
    private lateinit var ourBarChart: BarChart
    private lateinit var ourLineChart: LineChart
    /*private lateinit var bindingBar:GraphBarBinding
    private lateinit var bindingBar:GraphBarBinding
    private lateinit var bindingBar:GraphBarBinding
    private lateinit var bindingBar:GraphBarBinding*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val bundle = arguments
        //Log.d("Bundle",savedInstanceState.toString())
        val fragmentName = arguments!!.getString("frag_name")
        if(fragmentName.equals("Line Chart")){
            bindingLine = GraphLineBinding.inflate(inflater,container,false)
            ourLineChart = bindingLine.ourLineChart
            val growthArray = Array<Int>(6) {0}
            for (i in 1..6) {
                growthArray[i-1] = i+21/i
            }
            populateLineChart(growthArray)
            return bindingLine.root
        } else if(fragmentName.equals("Bar Chart")){
            bindingBar = GraphBarBinding.inflate(inflater,container,false)
            ourBarChart = bindingBar.ourBarChart
            val growthArray = Array<Int>(6) {0}
            for (i in 1..6) {
                growthArray[i-1] = i+66/i
            }
            populateBarChart(growthArray)
            return bindingBar.root
        } else if(fragmentName.equals("Pie Chart")){
            bindingPie = GraphPieBinding.inflate(inflater,container,false)
            ourPieChart = bindingPie.ourPieChart
            val growthArray = Array<Int>(6) {0}
            val nameArray = Array<String>(6){"nameo"}
            for (i in 1..6) {
                nameArray[i-1] = "name"+i
                growthArray[i-1] = i+21*i
            }
            populatePieChart(growthArray,nameArray)
            return bindingPie.root
        } else
            return super.onCreateView(inflater, container, savedInstanceState)
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
        ourPieChart.data = data
        //setting color and size of text
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(10f)

        //add an animation when rendering the pie chart
        ourPieChart.animateY(1400, Easing.EaseInOutQuad)
        //disabling center hole
        ourPieChart.isDrawHoleEnabled = false
        //do not show description text
        ourPieChart.description.isEnabled = false
        //legend enabled and its various appearance settings
        ourPieChart.legend.isEnabled = true
        ourPieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        ourPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        ourPieChart.legend.isWordWrapEnabled = true

        //dont show the text values on slices e.g Antelope, impala etc
        ourPieChart.setDrawEntryLabels(false)
        //refreshing the chart
        ourPieChart.invalidate()

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
        ourBarChart.data = data
        //setting the x-axis
        val xAxis: XAxis = ourBarChart.xAxis
        //calling methods to hide x-axis gridlines
        ourBarChart.axisLeft.setDrawGridLines(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove legend
        ourBarChart.legend.isEnabled = false

        //remove description label
        ourBarChart.description.isEnabled = false

        //add animation
        ourBarChart.animateY(3000)
        //refresh the chart
        ourBarChart.invalidate()
    }

    private fun populateLineChart(values: Array<Int>) {
        val ourLineChartEntries: ArrayList<Entry> = ArrayList()

        var i = 0

        for (entry in values) {
            var value = values[i].toFloat()
            ourLineChartEntries.add(Entry(i.toFloat(), value))
            i++
        }
        val lineDataSet = LineDataSet(ourLineChartEntries, "")
        lineDataSet.setColors(*ColorTemplate.PASTEL_COLORS)
        val data = LineData(lineDataSet)
        ourLineChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = ourLineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        ourLineChart.legend.isEnabled = false

        //remove description label
        ourLineChart.description.isEnabled = false

        //add animation
        ourLineChart.animateX(1000, Easing.EaseInSine)
        ourLineChart.data = data
        //refresh
        ourLineChart.invalidate()
    }

}