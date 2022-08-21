package com.sabeeh.graphkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sabeeh.graphkotlin.R
import com.sabeeh.graphkotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding//defining the binding class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)*/
        binding = ActivityMainBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root)
        val fragment = FragmentGraphList()
        //showFragment(fragment)
        binding.btnShow.setOnClickListener {

            /*val fragment = arb.test.fragment.Fragment_one()*/
            showFragment(fragment)

        }
    }
    fun showFragment(fragment: Fragment){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.fragment_main,fragment)
        fram.commit()
    }
}