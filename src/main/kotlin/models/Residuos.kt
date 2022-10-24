package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import java.io.File

/**
 * Residuos
 * @author Jorge y Alfredo
 * @since 19/10/2022
 * @property anio
 * @property mes
 * @property lote
 * @property residuos
 * @property distrito
 * @property nom_ditrito
 * @property toneladas
 * @constructor Create empty Residuos
 */
@DataSchema
data class Residuos(
    val anio: Int?,
    val mes: String,
    val lote: Int?,
    val residuos: String,
    val distrito: Int?,
    val nom_ditrito: String,
    val toneladas: Float
)



