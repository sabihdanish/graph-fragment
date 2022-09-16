package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.RadarChart
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding
import com.sabeeh.graphkotlin.databinding.GraphRadarBinding

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
        return binding.root
    }
}