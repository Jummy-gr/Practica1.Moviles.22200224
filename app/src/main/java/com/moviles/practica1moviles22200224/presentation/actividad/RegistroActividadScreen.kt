package com.moviles.practica1moviles22200224.presentation.actividad

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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.moviles.practica1moviles22200224.data.ActividadFisica


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroActividadScreen(navController: NavController) {
    var tipoActividad by remember { mutableStateOf("Correr") }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("Media") }
    var expanded by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val actividades = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de Actividad Física",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Dropdown para Tipo de Actividad
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = tipoActividad,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de actividad") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                actividades.forEach { actividad ->
                    DropdownMenuItem(
                        text = { Text(actividad) },
                        onClick = {
                            tipoActividad = actividad
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Duración
        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it; error = "" },
            label = { Text("Duración (minutos)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RadioButtons para Intensidad
        Text("Intensidad:", style = MaterialTheme.typography.bodyLarge)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = intensidad == "Baja",
                    onClick = { intensidad = "Baja" }
                )
                Text("Baja")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = intensidad == "Media",
                    onClick = { intensidad = "Media" }
                )
                Text("Media")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = intensidad == "Alta",
                    onClick = { intensidad = "Alta" }
                )
                Text("Alta")
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
                val duracionNum = duracion.toIntOrNull()

                when {
                    duracion.isBlank() -> {
                        error = "❌ Todos los campos son obligatorios"
                        resultado = ""
                    }
                    duracionNum == null -> {
                        error = "❌ La duración debe ser un número entero"
                        resultado = ""
                    }
                    duracionNum <= 0 -> {
                        error = "❌ La duración debe ser un número positivo"
                        resultado = ""
                    }
                    else -> {
                        val actividad = ActividadFisica(tipoActividad, duracionNum, intensidad)
                        resultado = actividad.obtenerMensajeCalorias()
                        error = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Calorías Quemadas")
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