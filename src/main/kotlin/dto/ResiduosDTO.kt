package dto

import kotlinx.serialization.Serializable

@Serializable
data class ResiduosDTO(
    val anio: Int?,
    val mes: String,
    val lote: Int?,
    val residuos: String,
    val distrito: Int?,
    val nom_ditrito: String,
    val toneladas: Float
)

