package com.sabeeh.graphkotlin.view

import androidx.lifecycle.ViewModel

class FragmentGraphViewModel :ViewModel() {
    var counter = 0

    fun increment() {
        counter++
    }
}