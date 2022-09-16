package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.charts.CombinedChart
import com.sabeeh.graphkotlin.databinding.GraphCandleStickBinding
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding

class CandleStickFragment: Fragment() {
    private lateinit var binding: GraphCandleStickBinding
    private lateinit var candleStickChart: CandleStickChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphCandleStickBinding.inflate(inflater,container,false)
        candleStickChart = binding.candleStickChart
        return binding.root
    }
}