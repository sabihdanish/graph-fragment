package com.sabeeh.graphkotlin.viewmodel

import androidx.lifecycle.ViewModel

class FragmentGraphViewModel :ViewModel() {
    var counter = 0

    fun increment() {
        counter++
    }
}