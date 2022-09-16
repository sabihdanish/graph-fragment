package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.ScatterChart
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding
import com.sabeeh.graphkotlin.databinding.GraphScatterBinding

class ScatterFragment: Fragment() {
    private lateinit var binding: GraphScatterBinding
    private lateinit var scatterChart: ScatterChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphScatterBinding.inflate(inflater,container,false)
        scatterChart = binding.scatterChart
        return binding.root
    }
}