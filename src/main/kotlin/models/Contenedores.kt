package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema


@DataSchema
data class Contenedores(
    val codInterno: Int,
    val tipoContenedor: String,
    val modelo: String,
    val descripcionModelo: String,
    val cantidad: Int,
    val lote: Int,
    val distrito: String,
    val barrio: String = "NO EXISTE",
    val tipoVia: String,
    val nombre: String,
    val numero: Int?,
    val cordenadax: String,
    val cordenaday: String,
    val longitud: String,
    val latitud: String,
    val direccion: String
)


