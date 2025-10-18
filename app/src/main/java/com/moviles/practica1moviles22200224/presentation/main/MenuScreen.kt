package com.moviles.practica1moviles22200224.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Menú Principal - Examen DAM",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("agua") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculadora de Consumo de Agua")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // En el botón de Actividad Física
        Button(
            onClick = { navController.navigate("actividad") },  // ← ACTUALIZADO
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registro de Actividad Física")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // En el botón de Autos Deportivos
        Button(
            onClick = { navController.navigate("autos") },  // ← ACTUALIZADO
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Catálogo de Autos Deportivos")
        }


    }
}