package com.moviles.practica1moviles22200224.presentation.autos


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.moviles.practica1moviles22200224.data.AutoDeportivo



// Lista de autos deportivos de ejemplo (Mock Data)
val autosDeportivos = listOf(
    AutoDeportivo(
        id = 1,
        marca = "Ferrari",
        modelo = "488 GTB",
        precio = 350000.00,
        imagenUrl = "https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=400"
    ),
    AutoDeportivo(
        id = 2,
        marca = "Lamborghini",
        modelo = "Huracán",
        precio = 320000.00,
        imagenUrl = "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=400"
    ),
    AutoDeportivo(
        id = 3,
        marca = "Porsche",
        modelo = "911 Turbo S",
        precio = 280000.00,
        imagenUrl = "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=400"
    ),
    AutoDeportivo(
        id = 4,
        marca = "McLaren",
        modelo = "720S",
        precio = 380000.00,
        imagenUrl = "https://images.unsplash.com/photo-1555215695-3004980ad54e?w=400"
    ),
    AutoDeportivo(
        id = 5,
        marca = "Aston Martin",
        modelo = "Vantage",
        precio = 190000.00,
        imagenUrl = "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=400"
    )
)

@Composable
fun CatalogoAutosScreen(navController: NavController) {
    val totalPrecios = autosDeportivos.sumOf { it.precio }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Título - FIJO en la parte superior
        Text(
            text = "Catálogo de Autos Deportivos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Lista de autos - OCUPA EL ESPACIO DISPONIBLE
        LazyColumn(
            modifier = Modifier
                .weight(1f)  // ← ESTO ES CLAVE: ocupa el espacio disponible
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(autosDeportivos) { auto ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Imagen del auto
                        Image(
                            painter = rememberAsyncImagePainter(auto.imagenUrl),
                            contentDescription = "Imagen de ${auto.marca} ${auto.modelo}",
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        // Información del auto
                        Column {
                            Text(
                                text = "${auto.marca} ${auto.modelo}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Precio: $${"%.2f".format(auto.precio)}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }

        // Sección inferior FIJA con total y botón
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Total acumulado de todos los autos
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Costo total: $${"%.2f".format(totalPrecios)}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Volver al Menú - SIEMPRE VISIBLE
            Button(
                onClick = { navController.navigate("menu") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Menú Principal")
            }
        }
    }
}