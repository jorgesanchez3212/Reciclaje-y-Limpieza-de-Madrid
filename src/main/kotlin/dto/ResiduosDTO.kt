package dto

import kotlinx.serialization.Serializable

/**
 * Residuos d t o
 * @author Jorge y Alfredo
 * @since 19/10/2022
 * @property anio
 * @property mes
 * @property lote
 * @property residuos
 * @property distrito
 * @property nom_ditrito
 * @property toneladas
 */
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

