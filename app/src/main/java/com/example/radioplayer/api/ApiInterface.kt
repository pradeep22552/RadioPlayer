package com.example.radioplayer.api


import com.example.radioplayer.model.NowPlayingResponse
import com.example.radioplayer.model.Nowplaying
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("testapi")
  //  fun getServices() : Call<List<Nowplaying>>
     fun getServices() : Call<List<Nowplaying>>
}