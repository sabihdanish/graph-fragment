package com.sabeeh.graphkotlin.view.fragments

import android.R
import android.graphics.RectF
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
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.sabeeh.graphkotlin.databinding.GraphHorizontalBarBinding


class HorizontalBarFragment: Fragment() ,OnSeekBarChangeListener,OnChartValueSelectedListener{
    private lateinit var binding: GraphHorizontalBarBinding
    private lateinit var horizontalBarChart: HorizontalBarChart
    private lateinit var seekBarX: SeekBar
    private  lateinit var seekBarY: SeekBar
    private lateinit var tvX: TextView
    private  lateinit var tvY: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphHorizontalBarBinding.inflate(inflater,container,false)
        horizontalBarChart = binding.horizontalBarChart
        tvX = binding.tvXMax
        tvY = binding.tvYMax

        seekBarX = binding.seekBarX
        seekBarX.setOnSeekBarChangeListener(this)

        seekBarY = binding.seekBarY
        seekBarY.setOnSeekBarChangeListener(this)
        horizontalBarChart.setOnChartValueSelectedListener(this)
        // chart.setHighlightEnabled(false);

        // chart.setHighlightEnabled(false);
        horizontalBarChart.setDrawBarShadow(false)

        horizontalBarChart.setDrawValueAboveBar(true)

        horizontalBarChart.getDescription().setEnabled(false)

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        horizontalBarChart.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately

        // scaling can now only be done on x- and y-axis separately
        horizontalBarChart.setPinchZoom(false)

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);


        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);
        horizontalBarChart.setDrawGridBackground(false)

        val xl: XAxis = horizontalBarChart.getXAxis()
        xl.position = XAxisPosition.BOTTOM
        xl.typeface = Typeface.SANS_SERIF
        xl.setDrawAxisLine(true)
        xl.setDrawGridLines(false)
        xl.granularity = 10f

        val yl: YAxis = horizontalBarChart.getAxisLeft()
        yl.typeface = Typeface.SANS_SERIF
        yl.setDrawAxisLine(true)
        yl.setDrawGridLines(true)
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)

//        yl.setInverted(true);

        //        yl.setInverted(true);
        val yr: YAxis = horizontalBarChart.getAxisRight()
        yr.typeface = Typeface.SANS_SERIF
        yr.setDrawAxisLine(true)
        yr.setDrawGridLines(false)
        yr.axisMinimum = 0f // this replaces setStartAtZero(true)

//        yr.setInverted(true);

        //        yr.setInverted(true);
        horizontalBarChart.setFitBars(true)
        horizontalBarChart.animateY(2500)

        // setting data

        // setting data
        seekBarY.progress = 50
        seekBarX.progress = 12

        val l: Legend = horizontalBarChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.formSize = 8f
        l.xEntrySpace = 4f
        return binding.root
    }

    private fun setData(count: Int, range: Int) {
        val barWidth = 9f
        val spaceForBar = 10f
        val values: ArrayList<BarEntry> = ArrayList()
        for (i in 0 until count) {
            val `val` = (Math.random() * range).toFloat()
            values.add(
                BarEntry(
                    i * spaceForBar, `val`,
                    //resources.getDrawable(R.drawable.star)
                )
            )
        }
        val set1: BarDataSet
        if (horizontalBarChart.getData() != null &&
            horizontalBarChart.getData().getDataSetCount() > 0
        ) {
            set1 = horizontalBarChart.getData().getDataSetByIndex(0) as BarDataSet
            set1.values = values
            horizontalBarChart.getData().notifyDataChanged()
            horizontalBarChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "DataSet 1")
            set1.setDrawIcons(false)
            val dataSets: ArrayList<IBarDataSet> = ArrayList()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.setValueTypeface(Typeface.SANS_SERIF)
            data.barWidth = barWidth
            horizontalBarChart.setData(data)
        }
    }


    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        tvX.setText(""+seekBarX.getProgress());
        tvY.setText(""+seekBarY.getProgress());

        setData(seekBarX.getProgress(), seekBarY.getProgress());
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.invalidate();
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
    private val mOnValueSelectedRectF = RectF()

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return

        val bounds: RectF = mOnValueSelectedRectF
        horizontalBarChart.getBarBounds(e as BarEntry?, bounds)

        val position: MPPointF = horizontalBarChart.getPosition(
            e,
            horizontalBarChart.getData()
                .getDataSetByIndex(h!!.getDataSetIndex())
                .getAxisDependency()
        )

        Log.i("bounds", bounds.toString())
        Log.i("position", position.toString())

        MPPointF.recycleInstance(position)
    }

    override fun onNothingSelected() {

    }
}