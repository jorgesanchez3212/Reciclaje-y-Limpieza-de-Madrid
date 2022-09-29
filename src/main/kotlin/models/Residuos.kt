package models

import java.io.File

data class Residuos(
    val anio: String,
    val mes: String,
    val lote: String,
    val residuos: String,
    val distrito: String,
    val nom_ditrito: String,
    val toneladas: String
)

fun loadResiduosFromCsv(csvFile: File): List<Residuos> {
    val residuos: List<Residuos> = csvFile.readLines()
        .drop(1)
        .map { it.split(";") }
        .map {
            it.map { campo -> campo.trim() }
            Residuos(
                anio = it[0],
                mes = it[1],
                lote = it[2],
                residuos = it[3],
                distrito = it[4],
                nom_ditrito = it[5],
                toneladas = it[6]
            )
        }
    return residuos


}

