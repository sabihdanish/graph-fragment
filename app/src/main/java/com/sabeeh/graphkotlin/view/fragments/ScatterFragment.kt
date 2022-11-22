package com.sabeeh.graphkotlin.view.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.sabeeh.graphkotlin.databinding.GraphScatterBinding
import com.sabeeh.graphkotlin.view.custom.CustomScatterShapeRenderer


/* https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/main/java/com/xxmassdeveloper/mpchartexample/ScatterChartActivity.java*/
class ScatterFragment: Fragment() , OnSeekBarChangeListener,OnChartValueSelectedListener {
    private lateinit var binding: GraphScatterBinding
    private lateinit var scatterChart: ScatterChart
    private lateinit var seekBarX: SeekBar
    private  lateinit var seekBarY:SeekBar
    private lateinit var tvX: TextView
    private  lateinit var tvY:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphScatterBinding.inflate(inflater,container,false)
        scatterChart = binding.scatterChart
        seekBarX = binding.seekBarX
        seekBarX.setOnSeekBarChangeListener(this)
        seekBarY = binding.seekBarY
        seekBarY.setOnSeekBarChangeListener(this)
        tvX = binding.tvXMax
        tvY = binding.tvYMax
        scatterChart.getDescription().setEnabled(false)
        scatterChart.setOnChartValueSelectedListener(this)

        scatterChart.setDrawGridBackground(false)
        scatterChart.setTouchEnabled(true)
        scatterChart.setMaxHighlightDistance(50f)

        // enable scaling and dragging

        // enable scaling and dragging
        scatterChart.setDragEnabled(true)
        scatterChart.setScaleEnabled(true)

        scatterChart.setMaxVisibleValueCount(200)
        scatterChart.setPinchZoom(true)

        seekBarX.progress = 45
        seekBarY.progress = 100

        val l: Legend = scatterChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.typeface = Typeface.SANS_SERIF
        l.xOffset = 5f

        val yl: YAxis = scatterChart.getAxisLeft()
        yl.typeface = Typeface.SANS_SERIF
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)


        scatterChart.getAxisRight().setEnabled(false)

        val xl: XAxis = scatterChart.getXAxis()
        xl.typeface = Typeface.SANS_SERIF
        xl.setDrawGridLines(false)
        return binding.root
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        tvX.setText(seekBarX.progress.toString())
        tvY.text = seekBarY.progress.toString()

        val values1: ArrayList<Entry> = ArrayList()
        val values2: ArrayList<Entry> = ArrayList()
        val values3: ArrayList<Entry> = ArrayList()

        for (i in 0 until seekBarX.progress) {
            val `val` = (Math.random() * seekBarY.progress).toFloat() + 3
            values1.add(Entry(i.toFloat(), `val`))
        }

        for (i in 0 until seekBarX.progress) {
            val `val` = (Math.random() * seekBarY.progress).toFloat() + 3
            values2.add(Entry(i + 0.33f, `val`))
        }

        for (i in 0 until seekBarX.progress) {
            val `val` = (Math.random() * seekBarY.progress).toFloat() + 3
            values3.add(Entry(i + 0.66f, `val`))
        }

        // create a dataset and give it a type

        // create a dataset and give it a type
        val set1 = ScatterDataSet(values1, "DS 1")
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE)
        set1.color = ColorTemplate.COLORFUL_COLORS[0]
        val set2 = ScatterDataSet(values2, "DS 2")
        set2.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
        set2.scatterShapeHoleColor = ColorTemplate.COLORFUL_COLORS[3]
        set2.scatterShapeHoleRadius = 3f
        set2.color = ColorTemplate.COLORFUL_COLORS[1]
        val set3 = ScatterDataSet(values3, "DS 3")
        set3.shapeRenderer = CustomScatterShapeRenderer()
        set3.color = ColorTemplate.COLORFUL_COLORS[2]

        set1.scatterShapeSize = 8f
        set2.scatterShapeSize = 8f
        set3.scatterShapeSize = 8f

        val dataSets: ArrayList<IScatterDataSet> = ArrayList()
        dataSets.add(set1) // add the data sets

        dataSets.add(set2)
        dataSets.add(set3)

        // create a data object with the data sets

        // create a data object with the data sets
        val data = ScatterData(dataSets)
        data.setValueTypeface(Typeface.SANS_SERIF)

        scatterChart.setData(data)
        scatterChart.invalidate()

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e != null) {
            if (h != null) {
                Log.i("VAL SELECTED",
                    "Value: " + e.getY() + ", xIndex: " + e.getX()
                            + ", DataSet index: " + h.getDataSetIndex())
            }
        };

    }

    override fun onNothingSelected() {

    }
}