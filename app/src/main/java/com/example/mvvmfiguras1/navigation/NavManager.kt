package com.example.mvvmfiguras1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmfiguras1.viewmodels.FigurasViewModel
import com.example.mvvmfiguras1.views.InicioView
import com.example.mvvmfiguras1.views.OpcionesView
import com.example.mvvmfiguras1.views.SobreAmiiboView

@Composable
fun NavManager(viewModel: FigurasViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ){
        composable("inicio"){
            InicioView(navController, viewModel)
        }
        //aqui añadiriamos un composable mas por cada vista que añadamos
        composable("sobreamiibo"){
            SobreAmiiboView(navController)
        }

        composable("opciones"){
            OpcionesView(navController)
        }

    }
}