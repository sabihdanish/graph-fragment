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

class FragmentGraphList : Fragment() {

    var text : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            Log.d("cont","Container not null")

        val v = inflater.inflate(R.layout.fragment_graph_list,container,false)
        text = v?.findViewById(R.id.txt_fragment)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text!!.setOnClickListener {
            Toast.makeText(activity,"text fragment",Toast.LENGTH_SHORT).show()
        }

    }

}