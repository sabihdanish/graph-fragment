package com.sabeeh.graphkotlin.viewmodel

import com.google.gson.JsonObject
import com.sabeeh.graphkotlin.model.OnResponseReceivedInterface
import com.sabeeh.retrofitprac.model.ApiInterface
import com.sabeeh.retrofitprac.model.ModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BLClass {
    private val apiInterface = ApiInterface.create().getMovies()
    lateinit var listener: OnResponseReceivedInterface

    fun init(l: OnResponseReceivedInterface) {
        listener = l
    }

    fun getResponse() {

        apiInterface.enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>?,
                response: Response<JsonObject>?) {

                if (response?.body() != null) {
                    var list: String= response?.body().toString()!!
                    listener.onResponseSuccess(list, response)
                }

            }
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                listener.onResponseFailure(t)
            }

        })
    }
}