package mappers

import Exceptions.FormatException
import dto.ContenedorDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Contenedores
import nl.adaptivity.xmlutil.serialization.XML
import repositories.SerializableContenedorDTO
import repositories.SerializableResiduosDTO
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
        if(fichero.exists()&&fichero.endsWith(".csv")) {
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
        throw FormatException("El formato no es correcto")
    }

    fun leerJSON(ruta:String): SerializableContenedorDTO{
        val fichero = File(ruta)
        if(fichero.exists()&&fichero.endsWith(".json")) {
            val json = Json { prettyPrint = true }
            return Json.decodeFromString(File(ruta).readText())
        }
        throw FormatException("El formato no es correcto")
    }

    fun exportarCSV(ruta: String, contenedores: SerializableContenedorDTO){
        val fichero = File(ruta+"contenedores.csv")
        fichero.writeText("codInterno;lote;tipoContendor;modelo;descripcionModelo;cantidad;lote;distrito;barrio;tipoVia;nombre" +
                ";numero;cordenadax;cordenaday;longitud;latitud;direccion\n")
        contenedores.contenedores.forEach { fichero.appendText("\n${it.codInterno};${it.tipoContenedor};" +
                "${it.modelo};${it.descripcionModelo};${it.cantidad};${it.lote};${it.distrito};${it.barrio};" +
                "${it.tipoVia};${it.nombre};${it.numero};${it.cordenadax};${it.cordenaday};${it.longitud};" +
                "${it.latitud};${it.direccion}") }

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
