package com.example.mvvmfiguras1.network

import com.example.mvvmfiguras1.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofit: APIfiguras by lazy{
        Retrofit.Builder().baseUrl(Constantes.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(APIfiguras::class.java)
    }
}