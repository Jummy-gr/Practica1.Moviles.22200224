package com.moviles.practica1moviles22200224.data

data class ActividadFisica(
    val tipoActividad: String,
    val duracion: Int,
    val intensidad: String
) {
    fun calcularCaloriasQuemadas(): Double {
        val caloriasPorMinuto = when (tipoActividad) {
            "Correr" -> 10.0
            "Caminar" -> 5.0
            "Nadar" -> 8.0
            "Ciclismo" -> 7.0
            "Yoga" -> 4.0
            else -> 0.0
        }

        val factorIntensidad = when (intensidad) {
            "Baja" -> 0.8
            "Media" -> 1.0
            "Alta" -> 1.2
            else -> 1.0
        }

        return caloriasPorMinuto * duracion * factorIntensidad
    }

    fun obtenerMensajeCalorias(): String {
        val calorias = calcularCaloriasQuemadas()
        return "Has quemado ${"%.2f".format(calorias)} calorías con $tipoActividad"
    }
}
