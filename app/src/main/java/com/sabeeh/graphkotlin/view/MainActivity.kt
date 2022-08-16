package com.sabeeh.graphkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sabeeh.graphkotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = FragmentGraphList()
        showFragment(fragment)
        /*btn_show.setOnClickListener {

            val fragment = arb.test.fragment.Fragment_one()
            showFragment(fragment)

        }*/
    }
    fun showFragment(fragment: Fragment){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.fragment_main,fragment)
        fram.commit()
    }
}