package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BubbleChart
import com.sabeeh.graphkotlin.databinding.GraphBubbleBinding

class BubbleFragment: Fragment() {
    private lateinit var binding: GraphBubbleBinding
    private lateinit var bubbleChart: BubbleChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GraphBubbleBinding.inflate(inflater,container,false)
        bubbleChart = binding.bubbleChart
        return binding.root
    }
}