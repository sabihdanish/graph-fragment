package com.sabeeh.graphkotlin.model

import com.google.gson.JsonObject
import com.sabeeh.retrofitprac.model.ModelClass
import retrofit2.Response

interface OnResponseReceivedInterface {
    fun onResponseSuccess(list: List<ModelClass> )
    fun onResponseSuccess(list: String , response : Response<JsonObject>?)
    fun onResponseFailure(t: Throwable?)
}