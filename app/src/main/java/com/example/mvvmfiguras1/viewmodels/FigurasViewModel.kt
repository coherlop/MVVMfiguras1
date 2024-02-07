package com.example.mvvmfiguras1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmfiguras1.models.FigurasLista
import com.example.mvvmfiguras1.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FigurasViewModel: ViewModel() {

    private val _figuras = MutableStateFlow<List<FigurasLista>>(emptyList())
    val figuras = _figuras.asStateFlow()

    init{
        obtenerFiguras()
    }

    private fun obtenerFiguras(){
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val response = RetrofitClient.retrofit.obtenerFiguras()
                _figuras.value = response.body()?.listaFiguras ?: emptyList()
            }
        }
    }

    //aqui agregaríamos los métodos para buscar por id, por serie, etc si quisieramos
}