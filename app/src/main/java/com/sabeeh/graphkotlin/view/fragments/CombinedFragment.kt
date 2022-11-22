package com.sabeeh.graphkotlin.view.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding
import kotlin.random.Random


class CombinedFragment: Fragment() {

    private lateinit var binding: GraphCombinedBinding
    private lateinit var combinedChart: CombinedChart
    private val count = 12;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphCombinedBinding.inflate(inflater,container,false)
        combinedChart = binding.combinedChart
        combinedChart.getDescription().setEnabled(false)
        combinedChart.setBackgroundColor(Color.WHITE)
        combinedChart.setDrawGridBackground(false)
        combinedChart.setDrawBarShadow(false)
        combinedChart.setHighlightFullBarEnabled(false)

        // draw bars behind lines

        // draw bars behind lines
        combinedChart.setDrawOrder(
            arrayOf(
                DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.CANDLE, DrawOrder.LINE, DrawOrder.SCATTER
            )
        )

        val l: Legend = combinedChart.getLegend()
        l.isWordWrapEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val rightAxis: YAxis = combinedChart.getAxisRight()
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        val leftAxis: YAxis = combinedChart.getAxisLeft()
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        val xAxis: XAxis = combinedChart.getXAxis()
        xAxis.position = XAxisPosition.BOTH_SIDED
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.setValueFormatter(IAxisValueFormatter { value, axis -> "X Axis"/*months.get(value.toInt() % months.length)*/ })

        val data = CombinedData()

        data.setData(generateLineData())
        data.setData(generateBarData())
        data.setData(generateBubbleData())
        data.setData(generateScatterData())
        data.setData(generateCandleData())
        data.setValueTypeface(Typeface.SANS_SERIF)

        xAxis.axisMaximum = data.xMax + 0.25f

        combinedChart.setData(data)
        combinedChart.invalidate()
        return binding.root
    }
    private fun generateLineData(): LineData? {
        val d = LineData()
        val entries: ArrayList<Entry> = ArrayList()
        for (index in 0 until count)
            entries.add(
            Entry(index + 0.5f,
                (5.. 15).random().toFloat())
            )

        val set = LineDataSet(entries, "Line DataSet")
        set.color = Color.rgb(240, 238, 70)
        set.lineWidth = 2.5f
        set.setCircleColor(Color.rgb(240, 238, 70))
        set.circleRadius = 5f
        set.fillColor = Color.rgb(240, 238, 70)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 10f
        set.valueTextColor = Color.rgb(240, 238, 70)
        set.axisDependency = YAxis.AxisDependency.LEFT
        d.addDataSet(set)
        return d
    }

    private fun generateBarData(): BarData? {
        val entries1: ArrayList<BarEntry> = ArrayList()
        val entries2: ArrayList<BarEntry> = ArrayList()
        for (index in 0 until count) {
            entries1.add(BarEntry(0f, (25..25).random().toFloat()))

            // stacked
            entries2.add(BarEntry(0f, floatArrayOf((12.. 13).random().toFloat(), (12.. 13).random().toFloat())))
        }
        val set1 = BarDataSet(entries1, "Bar 1")
        set1.color = Color.rgb(60, 220, 78)
        set1.valueTextColor = Color.rgb(60, 220, 78)
        set1.valueTextSize = 10f
        set1.axisDependency = YAxis.AxisDependency.LEFT
        val set2 = BarDataSet(entries2, "")
        set2.stackLabels = arrayOf("Stack 1", "Stack 2")
        set2.setColors(Color.rgb(61, 165, 255), Color.rgb(23, 197, 255))
        set2.valueTextColor = Color.rgb(61, 165, 255)
        set2.valueTextSize = 10f
        set2.axisDependency = YAxis.AxisDependency.LEFT
        val groupSpace = 0.06f
        val barSpace = 0.02f // x2 dataset
        val barWidth = 0.45f // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"
        val d = BarData(set1, set2)
        d.barWidth = barWidth

        // make this BarData object grouped
        d.groupBars(0f, groupSpace, barSpace) // start at x = 0
        return d
    }

    private fun generateScatterData(): ScatterData? {
        val d = ScatterData()
        val entries: ArrayList<Entry> = ArrayList()
        var index = 0f
        while (index < count) {
            entries.add(Entry(index + 0.25f, (10..55).random().toFloat()))
            index += 0.5f
        }
        val set = ScatterDataSet(entries, "Scatter DataSet")
        set.setColors(*ColorTemplate.MATERIAL_COLORS)
        set.scatterShapeSize = 7.5f
        set.setDrawValues(false)
        set.valueTextSize = 10f
        d.addDataSet(set)
        return d
    }

    private fun generateCandleData(): CandleData? {
        val d = CandleData()
        val entries: ArrayList<CandleEntry> = ArrayList()
        var index = 0
        while (index < count) {
            entries.add(CandleEntry(index + 1f, 90F, 70F, 85F, 75f))
            index += 2
        }
        val set = CandleDataSet(entries, "Candle DataSet")
        set.decreasingColor = Color.rgb(142, 150, 175)
        set.shadowColor = Color.DKGRAY
        set.barSpace = 0.3f
        set.valueTextSize = 10f
        set.setDrawValues(false)
        d.addDataSet(set)
        return d
    }

    private fun generateBubbleData(): BubbleData? {
        val bd = BubbleData()
        val entries: ArrayList<BubbleEntry> = ArrayList()
        for (index in 0 until count) {
            val y: Float =(10..105).random().toFloat()
            val size: Float = (100.. 105).random().toFloat()
            entries.add(BubbleEntry(index + 0.5f, y, size))
        }
        val set = BubbleDataSet(entries, "Bubble DataSet")
        set.setColors(*ColorTemplate.VORDIPLOM_COLORS)
        set.valueTextSize = 10f
        set.valueTextColor = Color.WHITE
        set.highlightCircleWidth = 1.5f
        set.setDrawValues(true)
        bd.addDataSet(set)
        return bd
    }
}