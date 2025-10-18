package com.moviles.practica1moviles22200224.presentation.navigate


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviles.practica1moviles22200224.presentation.actividad.RegistroActividadScreen
import com.moviles.practica1moviles22200224.presentation.agua.CalculadoraAguaScreen
import com.moviles.practica1moviles22200224.presentation.autos.CatalogoAutosScreen
import com.moviles.practica1moviles22200224.presentation.main.MenuScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") {
            MenuScreen(navController = navController)
        }
        composable("agua") {
            CalculadoraAguaScreen(navController = navController)
        }

        composable("actividad") {
            RegistroActividadScreen(navController = navController)
        }


        composable("autos") {
            CatalogoAutosScreen(navController = navController)
        }





    }
}