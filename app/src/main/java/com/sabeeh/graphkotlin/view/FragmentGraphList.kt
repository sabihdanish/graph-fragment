package com.sabeeh.graphkotlin.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabeeh.graphkotlin.databinding.FragmentGraphListBinding
import com.sabeeh.graphkotlin.viewmodel.FragmentGraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class FragmentGraphList : Fragment() {
    private lateinit var binding: FragmentGraphListBinding//defining the binding class
    private var customAdapter: CustomAdapter? = null
    private var graphArrayList: ArrayList<String>? = null
    //var text : TextView? = null
    lateinit var viewModel: FragmentGraphViewModel
    @Inject
    lateinit var sharedPreference: SharedPreferences
    @Inject
    lateinit var editor: SharedPreferences.Editor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentGraphListBinding.inflate(inflater,container,false)
        Log.d("cont","Container not null")
        graphArrayList = ArrayList()
        graphArrayList!!.add("Line Chart")
        graphArrayList!!.add("Bar Chart")
        graphArrayList!!.add("Pie Chart")
        graphArrayList!!.add("Scatter Chart")
        graphArrayList!!.add("Bubble Chart")
        graphArrayList!!.add("Candle Stick Chart")
        graphArrayList!!.add("Radar Chart")
        graphArrayList!!.add("Horizontal Bar Chart")
        graphArrayList!!.add("Combined Chart")
        customAdapter = CustomAdapter(graphArrayList!!)


        //FragmentGraphListBinding.inflate(inflater,container,false)

        //val v = inflater.inflate(R.layout.fragment_graph_list,container,false)
        //text = v?.findViewById(R.id.txt_fragment)
        viewModel = ViewModelProvider(this).get(FragmentGraphViewModel::class.java)

        //set shared prefs
        //val sharedPreference = this.context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        //var editor = sharedPreference.edit()
        editor?.putString("username","new editor")
        editor?.putLong("l",100L)
        editor?.commit()

        //to clear or remove
        /*editor.clear()
        editor.remove("username")*/

        //get prefs
        Log.d("Hilt Pref","Dagger Hilt "+sharedPreference.getString("username","defaultName"))
        sharedPreference.getLong("l",1L)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView!!.adapter = customAdapter

    }

}