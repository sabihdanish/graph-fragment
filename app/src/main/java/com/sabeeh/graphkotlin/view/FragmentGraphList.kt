package com.sabeeh.graphkotlin.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sabeeh.graphkotlin.R
import com.sabeeh.graphkotlin.databinding.ActivityMainBinding
import com.sabeeh.graphkotlin.databinding.FragmentGraphListBinding

class FragmentGraphList : Fragment() {
    private lateinit var binding: FragmentGraphListBinding//defining the binding class
    //var text : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentGraphListBinding.inflate(inflater,container,false)
        Log.d("cont","Container not null")
        //FragmentGraphListBinding.inflate(inflater,container,false)

        //val v = inflater.inflate(R.layout.fragment_graph_list,container,false)
        //text = v?.findViewById(R.id.txt_fragment)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.txtFragment.setOnClickListener {
            Toast.makeText(activity,"text fragment",Toast.LENGTH_SHORT).show()
        }

    }

}