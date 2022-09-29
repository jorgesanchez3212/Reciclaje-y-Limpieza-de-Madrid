package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import java.io.File


@DataSchema
data class Contenedores(
    val codInterno: Int,
    val tipoContenedor: String,
    val modelo: String,
    val descripcionModelo: String,
    val cantidad: Int,
    val lote: Int,
    val distrito: String,
    val barrio: String?,
    val tipoVia: String,
    val nombre: String,
    val numero: Int?,
    val cordenadax: String,
    val cordenaday: String,
    val longitud: String,
    val latitud: String,
    val direccion: String
)

//Procesar CSV
fun loadContenedoresCSV(csvFile: File):List<Contenedores>{
    var listaCSV : List<Contenedores> = csvFile.readLines()
        .drop(1)
        .map{contenedores -> contenedores.split(";")}

        .map{
            it.map{it.trim()}
            Contenedores(
                codInterno = it[0].toInt(),
                tipoContenedor = it[1],
                modelo = it[2],
                descripcionModelo = it[3],
                cantidad = it[4].toInt(),
                lote = it[5].toInt(),
                distrito = it[6],
                barrio =it[7],
                tipoVia =it[8],
                nombre =it[9],
                numero =it[10].toIntOrNull(),
                cordenadax =it[11],
                cordenaday = it[12],
                longitud =it[13],
                latitud =it[14],
                direccion = it[15]
            )
        }
    return listaCSV

}

