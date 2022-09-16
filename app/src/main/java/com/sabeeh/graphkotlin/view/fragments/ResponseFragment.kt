package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabeeh.graphkotlin.databinding.FragmentResponseBinding

class ResponseFragment: Fragment() {

    private lateinit var binding:FragmentResponseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResponseBinding.inflate(inflater,container,false)

        return binding.root
    }
}