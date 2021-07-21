package com.example.radioplayer.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.radioplayer.api.RetrofitClient
import com.example.radioplayer.model.NowPlayingResponse

import com.example.radioplayer.model.Nowplaying
import com.example.radioplayer.utils.Resultres
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NowplayingRepository {


  val NowplayingGetSet = MutableLiveData<List<Nowplaying>>()


   suspend fun getServicesApiCall():Resultres<List<Nowplaying>>{

        val call = RetrofitClient.apiInterface.getServices()
       call.enqueue(object : Callback<List<Nowplaying>> {
            override fun onFailure(call: Call<List<Nowplaying>>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }
            override  fun onResponse(
                call: Call<List<Nowplaying>>,
                response: Response<List<Nowplaying>>
            ) {
                // TODO("Not yet implemented")
           //     Log.v("DEBUG : ", response.body().toString())
            val     data = response.body()!!
                NowplayingGetSet.postValue(data)
            }
        })
    return Resultres.success(NowplayingGetSet.value)
    }
 }