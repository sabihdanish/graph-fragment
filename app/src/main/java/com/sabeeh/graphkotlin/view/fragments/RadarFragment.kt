package com.sabeeh.graphkotlin.view.fragments

import android.R
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.sabeeh.graphkotlin.databinding.GraphRadarBinding
import com.sabeeh.graphkotlin.view.custom.RadarMarkerView


class RadarFragment: Fragment() {
    private lateinit var binding: GraphRadarBinding
    private lateinit var radarChart: RadarChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphRadarBinding.inflate(inflater,container,false)
        radarChart = binding.radarChart
        radarChart.setBackgroundColor(Color.rgb(60, 65, 82))

        radarChart.getDescription().setEnabled(false)

        radarChart.setWebLineWidth(1f)
        radarChart.setWebColor(Color.LTGRAY)
        radarChart.setWebLineWidthInner(1f)
        radarChart.setWebColorInner(Color.LTGRAY)
        radarChart.setWebAlpha(100)

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        val mv: MarkerView = RadarMarkerView(context!!, com.sabeeh.graphkotlin.R.layout.radar_markerview)
        mv.chartView = radarChart // For bounds control

        radarChart.setMarker(mv) // Set the marker to the radarChart


        setData()

        radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad)

        val xAxis: XAxis = radarChart.getXAxis()
        xAxis.typeface = Typeface.SANS_SERIF
        xAxis.textSize = 9f
        xAxis.yOffset = 0f
        xAxis.xOffset = 0f
        xAxis.setValueFormatter( object : IAxisValueFormatter {
            private val mActivities = arrayOf("Burger", "Steak", "Salad", "Pasta", "Pizza")
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return mActivities[value.toInt() % mActivities.size]
            }
        })
        xAxis.textColor = Color.WHITE

        val yAxis: YAxis = radarChart.getYAxis()
        yAxis.typeface = Typeface.SANS_SERIF
        yAxis.setLabelCount(5, false)
        yAxis.textSize = 9f
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 80f
        yAxis.setDrawLabels(false)

        val l: Legend = radarChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.typeface = Typeface.SANS_SERIF
        l.xEntrySpace = 7f
        l.yEntrySpace = 5f
        l.textColor = Color.WHITE

        return binding.root
    }

    private fun setData() {
        val mul = 80f
        val min = 20f
        val cnt = 5
        val entries1: ArrayList<RadarEntry> = ArrayList()
        val entries2: ArrayList<RadarEntry> = ArrayList()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the radarChart.
        for (i in 0 until cnt) {
            val val1 = (Math.random() * mul).toFloat() + min
            entries1.add(RadarEntry(val1))
            val val2 = (Math.random() * mul).toFloat() + min
            entries2.add(RadarEntry(val2))
        }
        val set1 = RadarDataSet(entries1, "Last Week")
        set1.color = Color.rgb(103, 110, 129)
        set1.fillColor = Color.rgb(103, 110, 129)
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)
        val set2 = RadarDataSet(entries2, "This Week")
        set2.color = Color.rgb(121, 162, 175)
        set2.fillColor = Color.rgb(121, 162, 175)
        set2.setDrawFilled(true)
        set2.fillAlpha = 180
        set2.lineWidth = 2f
        set2.isDrawHighlightCircleEnabled = true
        set2.setDrawHighlightIndicators(false)
        val sets: ArrayList<IRadarDataSet> = ArrayList()
        sets.add(set1)
        sets.add(set2)
        val data = RadarData(sets)
        data.setValueTypeface(Typeface.SANS_SERIF)
        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)
        radarChart.setData(data)
        radarChart.invalidate()
    }

}

fun XAxis.setValueFormatter(iAxisValueFormatter: IAxisValueFormatter) {

}
