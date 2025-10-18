package com.moviles.practica1moviles22200224.data

data class ConsumoAgua(
    val nombre: String,
    val peso: Double,
    val genero: String
) {
    fun calcularLitrosRecomendados(): Double {
        val factor = when (genero) {
            "Masculino" -> 1.02
            "Femenino" -> 1.01
            else -> 1.00
        }
        return peso * 0.035 * factor
    }

    fun obtenerMensajeRecomendacion(): String {
        val litros = calcularLitrosRecomendados()
        return "$nombre debe beber aproximadamente ${"%.2f".format(litros)} litros de agua al día"
    }
}
