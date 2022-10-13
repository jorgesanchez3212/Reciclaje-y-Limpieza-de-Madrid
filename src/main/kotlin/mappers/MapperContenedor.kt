package mappers

import dto.ContenedorDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Contenedores
import nl.adaptivity.xmlutil.serialization.XML
import repositories.SerializableContenedorDTO
import java.io.File

class MapperContenedor () {


    fun toContenedorDTO(contenedores: Contenedores): ContenedorDTO {
        return ContenedorDTO(
            codInterno = contenedores.codInterno,
            tipoContenedor = contenedores.tipoContenedor,
            modelo = contenedores.modelo,
            descripcionModelo = contenedores.descripcionModelo,
            cantidad = contenedores.cantidad,
            lote = contenedores.lote,
            distrito = contenedores.distrito,
            barrio = contenedores.barrio,
            tipoVia = contenedores.tipoVia,
            nombre = contenedores.nombre,
            numero = contenedores.numero,
            cordenadax = contenedores.cordenadax,
            cordenaday = contenedores.cordenaday,
            longitud = contenedores.longitud,
            latitud = contenedores.latitud,
            direccion = contenedores.direccion
        )
    }

    fun toContenedores(contenedores: ContenedorDTO): Contenedores {
        return Contenedores(
            codInterno = contenedores.codInterno,
            tipoContenedor = contenedores.tipoContenedor,
            modelo = contenedores.modelo,
            descripcionModelo = contenedores.descripcionModelo,
            cantidad = contenedores.cantidad,
            lote = contenedores.lote,
            distrito = contenedores.distrito,
            barrio = contenedores.barrio,
            tipoVia = contenedores.tipoVia,
            nombre = contenedores.nombre,
            numero = contenedores.numero,
            cordenadax = contenedores.cordenadax,
            cordenaday = contenedores.cordenaday,
            longitud = contenedores.longitud,
            latitud = contenedores.latitud,
            direccion = contenedores.direccion
        )
    }


    fun leerCSV(ruta: String): List<ContenedorDTO> {
        val fichero = File(ruta)
        return fichero.readLines()
            .drop(1)
            .map { it.split(";") }
            .map {
                it.map { it.trim() }
                ContenedorDTO(
                    codInterno = it[0].toInt(),
                    tipoContenedor = it[1],
                    modelo = it[2],
                    descripcionModelo = it[3],
                    cantidad = it[4].toInt(),
                    lote = it[5].toInt(),
                    distrito = it[6],
                    barrio = it[7],
                    tipoVia = it[8],
                    nombre = it[9],
                    numero = it[10].toIntOrNull(),
                    cordenadax = it[11],
                    cordenaday = it[12],
                    longitud = it[13],
                    latitud = it[14],
                    direccion = it[15]
                )
            }
    }

    fun exportarJSON(ruta: String, contenedores: SerializableContenedorDTO) {
        val json = Json { prettyPrint = true }
        val fichero = File(ruta + File.separator + "ficheroC.json")
        fichero.writeText(json.encodeToString(contenedores))
    }

    fun exportarXML(ruta: String, contenedores: SerializableContenedorDTO) {
        val xml = XML { indentString = " " }
        val fichero = File(ruta + File.separator + "ficheroC.xml")
        fichero.writeText(xml.encodeToString(contenedores))
    }
}
