package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CombinedChart
import com.sabeeh.graphkotlin.databinding.GraphCombinedBinding

class CombinedFragment: Fragment() {

    private lateinit var binding: GraphCombinedBinding
    private lateinit var combinedChart: CombinedChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphCombinedBinding.inflate(inflater,container,false)
        combinedChart = binding.combinedChart
        return binding.root
    }
}