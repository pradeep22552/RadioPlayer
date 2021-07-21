package com.example.radioplayer.di

import com.example.radioplayer.BuildConfig
import com.example.radioplayer.Config
import com.example.radioplayer.api.RetrofitClient
import com.example.radioplayer.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val baseUrl = Config.BASE_URL
 

 @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
     val levelType: HttpLoggingInterceptor.Level
     if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
         levelType = HttpLoggingInterceptor.Level.BODY else levelType = HttpLoggingInterceptor.Level.NONE

     val logging = HttpLoggingInterceptor()
     logging.setLevel(levelType)
     return logging;
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
                .addInterceptor(AuthInterceptor())
            .build()

    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }



}