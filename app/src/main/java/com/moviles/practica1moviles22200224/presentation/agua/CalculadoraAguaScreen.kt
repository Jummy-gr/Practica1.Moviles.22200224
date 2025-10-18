package com.moviles.practica1moviles22200224.presentation.agua

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.moviles.practica1moviles22200224.data.ConsumoAgua


@Composable
fun CalculadoraAguaScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("Sin especificar") }
    var resultado by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Consumo de Agua",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it; error = "" },
            label = { Text("Nombre de la persona") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Peso
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it; error = "" },
            label = { Text("Peso corporal (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RadioButtons para Género
        Text("Género:", style = MaterialTheme.typography.bodyLarge)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = genero == "Masculino",
                    onClick = { genero = "Masculino" }
                )
                Text("Masculino")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = genero == "Femenino",
                    onClick = { genero = "Femenino" }
                )
                Text("Femenino")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = genero == "Sin especificar",
                    onClick = { genero = "Sin especificar" }
                )
                Text("Sin especificar")
            }
        }

        // Mensaje de error
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Calcular
        Button(
            onClick = {
                val pesoNum = peso.toDoubleOrNull()

                when {
                    nombre.isBlank() || peso.isBlank() -> {
                        error = "❌ Todos los campos son obligatorios"
                        resultado = ""
                    }
                    pesoNum == null -> {
                        error = "❌ El peso debe ser un número"
                        resultado = ""
                    }
                    pesoNum < 5 || pesoNum > 200 -> {
                        error = "❌ El peso debe estar entre 5 y 200 kg"
                        resultado = ""
                    }
                    else -> {
                        val consumo = ConsumoAgua(nombre, pesoNum, genero)
                        resultado = consumo.obtenerMensajeRecomendacion()
                        error = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Consumo de Agua")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar resultado
        if (resultado.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = resultado,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Volver al Menú
        Button(
            onClick = { navController.navigate("menu") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Menú Principal")
        }
    }
}