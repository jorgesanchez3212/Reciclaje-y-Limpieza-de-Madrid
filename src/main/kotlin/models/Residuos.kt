package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import java.io.File
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



