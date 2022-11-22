package com.sabeeh.graphkotlin.view.fragments

import android.R
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.sabeeh.graphkotlin.databinding.GraphCandleStickBinding
import java.lang.String
import kotlin.Boolean
import kotlin.Int


class CandleStickFragment: Fragment() ,OnSeekBarChangeListener{
    private lateinit var binding: GraphCandleStickBinding
    private lateinit var candleStickChart: CandleStickChart
    private lateinit var seekBarX: SeekBar
    private  lateinit var seekBarY: SeekBar
    private lateinit var tvX: TextView
    private  lateinit var tvY: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphCandleStickBinding.inflate(inflater,container,false)
        candleStickChart = binding.candleStickChart
        seekBarX = binding.seekBarX
        seekBarX.setOnSeekBarChangeListener(this)
        seekBarY = binding.seekBarY
        seekBarY.setOnSeekBarChangeListener(this)
        tvX = binding.tvXMax
        tvY = binding.tvYMax

        candleStickChart.setBackgroundColor(Color.WHITE)

        candleStickChart.getDescription().setEnabled(false)

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        candleStickChart.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately

        // scaling can now only be done on x- and y-axis separately
        candleStickChart.setPinchZoom(false)

        candleStickChart.setDrawGridBackground(false)

        val xAxis: XAxis = candleStickChart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)

        val leftAxis: YAxis = candleStickChart.getAxisLeft()
//        leftAxis.setEnabled(false);
        //        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)

        val rightAxis: YAxis = candleStickChart.getAxisRight()
        rightAxis.isEnabled = false
//        rightAxis.setStartAtZero(false);

        // setting data
        //        rightAxis.setStartAtZero(false);

        // setting data
        seekBarX.progress = 40
        seekBarY.progress = 100

        candleStickChart.getLegend().setEnabled(false)
        return binding.root
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var progress = seekBarX.progress

        tvX.setText(String.valueOf(progress))
        tvY.text = seekBarY.progress.toString()

        candleStickChart.resetTracking()

        val values: ArrayList<CandleEntry> = ArrayList()

        for (i in 0 until progress) {
            val multi = (seekBarY.progress + 1).toFloat()
            val `val` = (Math.random() * 40).toFloat() + multi
            val high = (Math.random() * 9).toFloat() + 8f
            val low = (Math.random() * 9).toFloat() + 8f
            val open = (Math.random() * 6).toFloat() + 1f
            val close = (Math.random() * 6).toFloat() + 1f
            val even = i % 2 == 0
            values.add(
                CandleEntry(
                    i.toFloat(), `val` + high,
                    `val` - low,
                    if (even) `val` + open else `val` - open,
                    if (even) `val` - close else `val` + close,
                    //resources.getDrawable(R.drawable.star)
                )
            )
        }

        val set1 = CandleDataSet(values, "Data Set")

        set1.setDrawIcons(false)
        set1.axisDependency = AxisDependency.LEFT
//        set1.setColor(Color.rgb(80, 80, 80));
        //        set1.setColor(Color.rgb(80, 80, 80));
        set1.shadowColor = Color.DKGRAY
        set1.shadowWidth = 0.7f
        set1.decreasingColor = Color.RED
        set1.decreasingPaintStyle = Paint.Style.FILL
        set1.increasingColor = Color.rgb(122, 242, 84)
        set1.increasingPaintStyle = Paint.Style.STROKE
        set1.neutralColor = Color.BLUE
        //set1.setHighlightLineWidth(1f);

        //set1.setHighlightLineWidth(1f);
        val data = CandleData(set1)

        candleStickChart.setData(data)
        candleStickChart.invalidate()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}