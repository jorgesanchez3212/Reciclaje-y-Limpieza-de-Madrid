package mappers

import exceptions.FormatException
import dto.ResiduosDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Residuos
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File


class MapperResiduos {
    fun toResiduosDTO(residuos: Residuos): ResiduosDTO {
        return ResiduosDTO(
            anio = residuos.anio,
            mes = residuos.mes,
            lote = residuos.lote,
            residuos = residuos.residuos,
            distrito = residuos.distrito,
            nom_ditrito = residuos.nom_ditrito,
            toneladas = residuos.toneladas
        )
    }

    fun toResiduos(residuos: ResiduosDTO): Residuos {
        return Residuos(
            anio = residuos.anio,
            mes = residuos.mes,
            lote = residuos.lote,
            residuos = residuos.residuos,
            distrito = residuos.distrito,
            nom_ditrito = residuos.nom_ditrito,
            toneladas = residuos.toneladas
        )
    }

    fun leerCSV(ruta: String): List<ResiduosDTO> {
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".csv")) {
            return fichero.readLines()
                .drop(1)
                .map { residuos -> residuos.split(";") }
                .map {
                    it.map { it.trim() }
                    ResiduosDTO(
                        anio = it[0].toIntOrNull(),
                        mes = it[1],
                        lote = it[2].toIntOrNull(),
                        residuos = it[3],
                        distrito = it[4].toIntOrNull(),
                        nom_ditrito = it[5],
                        toneladas = it[6].replace(",", ".").toFloat()
                    )
                }
        }else {

            throw FormatException("El formato no es correcto")
        }

    }

    fun leerJSON(ruta:String): List<ResiduosDTO>{
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".json")) {
            val json = Json { prettyPrint = true }
            return Json.decodeFromString(File(ruta).readText())
        }else {
            throw FormatException("El formato no es correcto")
        }
    }

    fun leerXML(ruta:String):List<ResiduosDTO>{
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".xml")) {
            val xml = XML { indentString = " " }
            return XML.decodeFromString(fichero.readText())
        }else {
            throw FormatException("El formato no es correcto")
        }
    }

    fun copiarCSV(ruta: String,rutaDestino: String){
        val fichero = File(ruta+File.separator+"modelo_residuos_2021.csv")
        fichero.copyTo(File(rutaDestino+File.separator+"modelo_residuos_2021.csv"))
    }

    fun exportarCSV(ruta: String, residuos: List<ResiduosDTO>){
        val fichero = File(ruta+File.separator+"residuos.csv")
        fichero.writeText("anio;mes;lote;residuos;distrito;nom_ditrito;toneladas\n")
        residuos.forEach { fichero.appendText("\n${it.anio};${it.mes};" +
                "${it.lote};${it.residuos};${it.distrito};${it.lote};${it.distrito};${it.nom_ditrito};" +
                "${it.toneladas}") }
    }

    fun exportarJSON(ruta: String, residuos: List<ResiduosDTO>) {
        val json= Json { prettyPrint=true }
        val fichero=File(ruta+File.separator+"ficheroR.json")
        fichero.writeText(json.encodeToString(residuos))
    }


    fun exportarXML(ruta: String, residuos:List<ResiduosDTO>){
        val xml= XML{indentString=" "}
        val fichero=File(ruta+File.separator+"ficheroR.xml")
        fichero.writeText(xml.encodeToString(residuos))
    }
}