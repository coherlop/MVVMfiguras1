package com.example.mvvmfiguras1.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmfiguras1.components.MainTopBar
import com.example.mvvmfiguras1.models.NavigationItem
import com.example.mvvmfiguras1.viewmodels.FigurasViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mvvmfiguras1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreAmiiboView(navController: NavController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }


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
            ContenidoSobreView(
                navController = navController,
                pad = it
            )
        }
    }


}


@Composable
fun ContenidoSobreView(navController: NavController, pad: PaddingValues){

    Column(modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        
        Image(painter = painterResource(id = R.drawable.amiibofoto),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth() )

        Text(text = "¿Qué son los amiibo?",
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp))
        Text(text = "Los amiibo son figuras y tarjetas interactivas que puedes usar con tus juegos. " +
                "Usa un amiibo mientras estás jugando a un juego compatible de Nintendo Switch, " +
                "Nintendo 3DS o Wii U y descubrirás sorprendentes nuevas funciones. " +
                "Los amiibo tienen diferentes efectos dependiendo del juego. " +
                "Es posible que desbloquees nuevos modos, armas o atuendos para personajes, " +
                "o incluso que mejores las destrezas del amiibo para convertirlo en el aliado perfecto, " +
                "¡o en el rival más mortífero!",
            textAlign =TextAlign.Justify,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(20.dp))

    }
}