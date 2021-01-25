package com.alex.themoviedb.data.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

//    private lateinit var retrofit: Retrofit;

    /*init {
        retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

    private fun getRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .build()
    }

    val getMovieService: MovieService = getRetrofit().create(MovieService::class.java)
}