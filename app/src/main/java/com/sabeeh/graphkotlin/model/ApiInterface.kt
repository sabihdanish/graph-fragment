package com.sabeeh.retrofitprac.model

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("channels/1626335/feeds.json?api_key=EEACUN3DNQ2KCGTN&results=8")
    //@GET("v3/articles")
    fun getMovies() : Call<JsonObject>

    companion object {
//https://api.thingspeak.com/channels/1626335/feeds.json?api_key=EEACUN3DNQ2KCGTN&results=2

        var BASE_URL = "https://api.thingspeak.com/"
        //var BASE_URL = "https://api.spaceflightnewsapi.net/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}