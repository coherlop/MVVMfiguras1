package com.example.mvvmfiguras1.components

import android.preference.PreferenceManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mvvmfiguras1.R
import com.example.mvvmfiguras1.models.FigurasLista
import com.example.mvvmfiguras1.ui.theme.BlancoRoto
import com.example.mvvmfiguras1.ui.theme.RojoAmiibo
import com.example.mvvmfiguras1.utils.Constantes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(titulo: String){
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(
                    text = titulo,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            }

        },
        // The navigation icon is set to a menu icon.
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(Icons.Filled.Menu, contentDescription = "Navigation Icon")
            }
        },

        // The action icon is set to a favorite icon.
        actions = {
            IconButton(onClick = { /* Handle navigation icon click */ }) {
                Image(
                    painterResource(id = R.drawable.amiiboicon),
                    null,
                    modifier = Modifier.height(30.dp).padding(2.dp)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = BlancoRoto
        )
    )
}

@Composable
fun CardFigura(
    figura: FigurasLista,
    onClick: () -> Unit
){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(15.dp)
            .shadow(40.dp)
            .clickable { onClick() }
    ){
        Column{
            InicioImagen(imagen = figura.imagen)
        }
    }
}

//@Composable
//fun InicioImagen(imagen:String){
//    val imagen = rememberImagePainter(data = imagen)
//    val saturacion = remember { mutableStateOf(0f) }
//    val scope = rememberCoroutineScope()
//
//    Image(
//        modifier = Modifier
//            .height(200.dp)
//            .width(200.dp)
//            .background(Color.White)
//            .clickable {
//                scope.launch {
//                    if (saturacion.value == 0f) {
//                        saturacion.value = 1f
//                    } else {
//                        saturacion.value = 0f
//                    }
//                }
//            },
//        painter = imagen,
//        contentDescription = null,
//        contentScale = ContentScale.Fit,
//        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(saturacion.value) })
//    )
//}

@Composable
fun InicioImagen(imagen:String){
    val imagen = rememberImagePainter(data = imagen)
    val context = LocalContext.current
    val prefs = remember { PreferenceManager.getDefaultSharedPreferences(context) }
    val saturacion = remember { mutableStateOf(prefs.getFloat(imagen.toString(), 0f)) }
    val scope = rememberCoroutineScope()

    Image(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .background(Color.White)
            .clickable {
                scope.launch {
                    if (saturacion.value == 0f) {
                        saturacion.value = 1f
                    } else {
                        saturacion.value = 0f
                    }
                    prefs
                        .edit()
                        .putFloat(imagen.toString(), saturacion.value)
                        .apply()
                }
            },
        painter = imagen,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(saturacion.value) })
    )
}

//@Composable
//fun InicioImagen(imagen:String){
//    val imagen = rememberImagePainter(data = imagen)
//    val context = LocalContext.current
//    val prefs = remember { PreferenceManager.getDefaultSharedPreferences(context) }
//    val saturaciones = remember { mutableStateMapOf<String, Float>() }
//    val scope = rememberCoroutineScope()
//
//    val saturacion = saturaciones.getOrPut(imagen.toString()) {
//        prefs.getFloat(imagen.toString(), 0f)
//    }
//
//    Image(
//        modifier = Modifier
//            .height(200.dp)
//            .width(200.dp)
//            .background(Color.White)
//            .clickable {
//                scope.launch {
//                    if (saturacion == 0f) {
//                        saturaciones[imagen.toString()] = 1f
//                    } else {
//                        saturaciones[imagen.toString()] = 0f
//                    }
//                    prefs.edit().putFloat(imagen.toString(), saturaciones[imagen]!!).apply()
//                }
//            },
//        painter = imagen,
//        contentDescription = null,
//        contentScale = ContentScale.Fit,
//        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(saturacion) })
//    )
//}



