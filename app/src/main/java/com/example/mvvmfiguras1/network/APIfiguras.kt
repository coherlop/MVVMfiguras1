package com.example.mvvmfiguras1.network

import com.example.mvvmfiguras1.models.FiguraModel
import retrofit2.Response
import retrofit2.http.GET

interface APIfiguras {

    @GET("amiibo/?type=figure")
    suspend fun obtenerFiguras(): Response<FiguraModel>
}