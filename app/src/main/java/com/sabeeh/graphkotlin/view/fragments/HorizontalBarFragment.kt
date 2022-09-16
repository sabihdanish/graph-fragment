package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.sabeeh.graphkotlin.databinding.GraphHorizontalBarBinding

class HorizontalBarFragment: Fragment() {
    private lateinit var binding: GraphHorizontalBarBinding
    private lateinit var horizontalBarChart: HorizontalBarChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphHorizontalBarBinding.inflate(inflater,container,false)
        horizontalBarChart = binding.horizontalBarChart
        return binding.root
    }
}