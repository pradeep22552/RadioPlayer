package com.example.radioplayer.api

import com.example.radioplayer.model.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET

interface  SongsApiInterface {
    @GET("testapi")
    suspend fun getSongs() : Response<NowPlayingResponse>
}