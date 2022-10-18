package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
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
        return binding.root
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

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