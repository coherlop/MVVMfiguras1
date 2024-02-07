package com.example.mvvmfiguras1.models

import com.google.gson.annotations.SerializedName

class FiguraModel (

    @SerializedName("amiibo")
    val listaFiguras: List<FigurasLista>

)

data class FigurasLista(
    @SerializedName("name")
    val nombre: String,
    @SerializedName("image")
    val imagen: String
)