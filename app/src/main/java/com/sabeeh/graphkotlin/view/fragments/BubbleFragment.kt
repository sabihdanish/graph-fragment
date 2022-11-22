package com.sabeeh.graphkotlin.view.fragments

import android.R
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.sabeeh.graphkotlin.databinding.GraphBubbleBinding


/*https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/main/java/com/xxmassdeveloper/mpchartexample/BubbleChartActivity.java*/
class BubbleFragment: Fragment() , SeekBar.OnSeekBarChangeListener,
    OnChartValueSelectedListener {
    private lateinit var binding: GraphBubbleBinding
    private lateinit var bubbleChart: BubbleChart
    private lateinit var seekBarX: SeekBar
    private  lateinit var seekBarY:SeekBar
    private lateinit var tvX: TextView
    private  lateinit var tvY:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphBubbleBinding.inflate(inflater,container,false)
        bubbleChart = binding.bubbleChart
        tvX = binding.tvXMax
        tvY = binding.tvYMax

        seekBarX = binding.seekBarX
        seekBarX.setOnSeekBarChangeListener(this)

        seekBarY = binding.seekBarY
        seekBarY.setOnSeekBarChangeListener(this)

        bubbleChart.getDescription().setEnabled(false)

        bubbleChart.setOnChartValueSelectedListener(this)

        bubbleChart.setDrawGridBackground(false)

        bubbleChart.setTouchEnabled(true)

        // enable scaling and dragging

        // enable scaling and dragging
        bubbleChart.setDragEnabled(true)
        bubbleChart.setScaleEnabled(true)

        bubbleChart.setMaxVisibleValueCount(200)
        bubbleChart.setPinchZoom(true)

        seekBarX.progress = 10
        seekBarY.progress = 50

        val l: Legend = bubbleChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.typeface = Typeface.SANS_SERIF

        val yl: YAxis = bubbleChart.getAxisLeft()
        yl.typeface = Typeface.SANS_SERIF
        yl.spaceTop = 30f
        yl.spaceBottom = 30f
        yl.setDrawZeroLine(false)

        bubbleChart.getAxisRight().setEnabled(false)

        val xl: XAxis = bubbleChart.getXAxis()
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.typeface = Typeface.SANS_SERIF
        return binding.root
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val count = seekBarX.progress
        val range = seekBarY.progress

        tvX.text = count.toString()
        tvY.text = range.toString()

        val values1: ArrayList<BubbleEntry> = ArrayList()
        val values2: ArrayList<BubbleEntry> = ArrayList()
        val values3: ArrayList<BubbleEntry> = ArrayList()

        for (i in 0 until count) {
            values1.add(
                BubbleEntry(
                    i.toFloat(),
                    (Math.random() * range).toFloat(),
                    (Math.random() * range).toFloat(),
                    //resources.getDrawable(R.drawable.star)
                )
            )
            values2.add(
                BubbleEntry(
                    i.toFloat(),
                    (Math.random() * range).toFloat(),
                    (Math.random() * range).toFloat(),
                    //resources.getDrawable(R.drawable.star)
                )
            )
            values3.add(
                BubbleEntry(
                    i.toFloat(),
                    (Math.random() * range).toFloat(),
                    (Math.random() * range).toFloat()
                )
            )
        }

        // create a dataset and give it a type

        // create a dataset and give it a type
        val set1 = BubbleDataSet(values1, "DS 1")
        set1.setDrawIcons(false)
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0], 130)
        set1.setDrawValues(true)

        val set2 = BubbleDataSet(values2, "DS 2")
        set2.setDrawIcons(false)
        set2.iconsOffset = MPPointF(0F, 15F)
        set2.setColor(ColorTemplate.COLORFUL_COLORS[1], 130)
        set2.setDrawValues(true)

        val set3 = BubbleDataSet(values3, "DS 3")
        set3.setColor(ColorTemplate.COLORFUL_COLORS[2], 130)
        set3.setDrawValues(true)

        val dataSets: ArrayList<IBubbleDataSet> = ArrayList()
        dataSets.add(set1) // add the data sets

        dataSets.add(set2)
        dataSets.add(set3)

        // create a data object with the data sets

        // create a data object with the data sets
        val data = BubbleData(dataSets)
        data.setDrawValues(false)
        data.setValueTypeface(Typeface.SANS_SERIF)
        data.setValueTextSize(8f)
        data.setValueTextColor(Color.WHITE)
        data.setHighlightCircleWidth(1.5f)

        bubbleChart.setData(data)
        bubbleChart.invalidate()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {

    }
}