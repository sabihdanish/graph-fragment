package com.sabeeh.graphkotlin.view

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sabeeh.graphkotlin.databinding.FragmentGraphListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentGraphList : Fragment() {
    private lateinit var binding: FragmentGraphListBinding//defining the binding class
    //var text : TextView? = null
    lateinit var viewModel:FragmentGraphViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentGraphListBinding.inflate(inflater,container,false)
        Log.d("cont","Container not null")
        //FragmentGraphListBinding.inflate(inflater,container,false)

        //val v = inflater.inflate(R.layout.fragment_graph_list,container,false)
        //text = v?.findViewById(R.id.txt_fragment)
        viewModel = ViewModelProvider(this).get(FragmentGraphViewModel::class.java)

        //set shared prefs
        val sharedPreference = this.context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        editor?.putString("username","Anupam")
        editor?.putLong("l",100L)
        editor?.commit()

        //to clear or remove
        /*editor.clear()
        editor.remove("username")*/

        //get prefs
        sharedPreference?.getString("username","defaultName")
        sharedPreference?.getLong("l",1L)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.txtFragment.text = viewModel.counter.toString()

        binding.txtFragment.setOnClickListener {
            Toast.makeText(activity,"text fragment",Toast.LENGTH_SHORT).show()
            viewModel.increment()
            binding.txtFragment.text = viewModel.counter.toString()
        }

    }

}