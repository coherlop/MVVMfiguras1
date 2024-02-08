package com.example.mvvmfiguras1.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvmfiguras1.R
import com.example.mvvmfiguras1.components.CardFigura
import com.example.mvvmfiguras1.components.MainTopBar
import com.example.mvvmfiguras1.models.NavigationItem
import com.example.mvvmfiguras1.ui.theme.AguamarinaAmiibo
import com.example.mvvmfiguras1.viewmodels.FigurasViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController: NavController, viewModel: FigurasViewModel){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0)}


    val items = listOf(
        NavigationItem(
            title = "Mi colección",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            "inicio"
        ),
        NavigationItem(
            title = "Sobre Amiibo",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            "sobreamiibo"
        ),
        NavigationItem(
            title = "Opciones",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            "opciones"
        ),
    )

    ModalNavigationDrawer(
        drawerContent = { //opciones dentro del menu
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(16.dp))
                            items.forEachIndexed { index, item ->
                                NavigationDrawerItem(
                                    label = { Text(text = item.title)},
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        navController.navigate(item.route)
                                        selectedItemIndex = index
                                            scope.launch{
                                                drawerState.close()
                                            }
                                    },
                                    icon = {
                                        Icon(imageVector = if(index == selectedItemIndex) {
                                        item.selectedIcon
                                    }else item.unselectedIcon,
                                        contentDescription = item.title )
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

                                )
                            }
                        }
                         },
        drawerState = drawerState
        )
    { //resto de la app
        Scaffold (
            topBar = {
                MainTopBar(titulo = "my amiibo collection", drawerState, scope)
            }
        ){
            ContenidoInicioView(
                navController = navController,
                viewModel = viewModel,
                pad = it
            )
        }
    }


}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun InicioView(navController: NavController, viewModel: FigurasViewModel){
//    Scaffold (
//        topBar = {
//            MainTopBar(titulo = "my amiibo collection")
//            }
//    ){
//        ContenidoInicioView(
//            navController = navController,
//            viewModel = viewModel,
//            pad = it
//        )
//    }
//}


@Composable
fun ContenidoInicioView(
    navController: NavController,
    viewModel: FigurasViewModel,
    pad: PaddingValues
){
    val figuras by viewModel.figuras.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondoamiibo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 90.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            modifier = Modifier.background(Color.Transparent),
            content = {
                items(figuras) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CardFigura(figura = it) {  }
                        Text(
                            text = it.nombre,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = AguamarinaAmiibo,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            })
    }

        //    LazyColumn(
    //        modifier = Modifier
    //            .padding(pad)
    //            .background(Color(Constantes.COLOR_ROJO))
    //    ){
    //        items(figuras) {
    //            Column (horizontalAlignment = Alignment.CenterHorizontally){
    //                CardFigura(figura = it) { }
    //                Text(
    //                    text = it.nombre,
    //                    fontFamily = FontFamily.SansSerif,
    //                    fontWeight = FontWeight.ExtraBold,
    //                    color = Color.Black,
    //                    modifier = Modifier.padding(start = 12.dp)
    //                )
    //            }
    //
    //        }
    //    }


}