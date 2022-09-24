package com.sabeeh.graphkotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.sabeeh.graphkotlin.databinding.FragmentResponseBinding
import com.sabeeh.graphkotlin.model.OnResponseReceivedInterface
import com.sabeeh.graphkotlin.viewmodel.BLClass
import com.sabeeh.retrofitprac.model.ModelClass
import retrofit2.Response

class ResponseFragment: Fragment(),OnResponseReceivedInterface {

    private lateinit var binding:FragmentResponseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResponseBinding.inflate(inflater,container,false)
        val blClass = BLClass()
        blClass.init(this)
        blClass.getResponse()
        return binding.root
    }

    override fun onResponseSuccess(list: List<ModelClass>) {
        //
    }

    override fun onResponseSuccess(list: String, response: Response<JsonObject>?) {
        //TODO("Not yet implemented")
        binding.responseRec.text = response?.body()?.getAsJsonArray("feeds")?.toList().toString()
        //binding.responsetv.text = response?.body()?.getAsJsonArray("feeds").toString()
        Log.d("Response Success",""+list)
        Log.d("Response resSuccess",""+response?.body()?.getAsJsonArray("feeds")?.toList().toString())
    }

    override fun onResponseFailure(t: Throwable?) {
        //TODO("Not yet implemented")
        binding.responseRec.text = t.toString()
        Log.d("Response Failure",""+t.toString())
    }
}