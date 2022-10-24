package mappers

import dto.ResiduosDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Residuos
import mu.KotlinLogging
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File


/**
 * Mapper residuos
 * @author Jorge y Alfredo
 * @since 19/10/2022
 * @constructor Create empty Mapper residuos
 */
class MapperResiduos {

    val logger = KotlinLogging.logger{}

    /**
     * To residuos d t o
     *
     * @param residuos
     * @return ResiduosDTO
     */
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

    /**
     * To residuos
     *
     * @param residuos
     * @return Residuos
     */
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

    /**
     * Leer c s v
     *
     * @param ruta
     * @return List<ResiduosDTO>
     */
    fun leerCSV(ruta: String): List<ResiduosDTO> {
        logger.debug { "Leyendo archivo csv" }
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".csv")) {
            if(fichero.readLines().take(1).first().split(";").size == 7) {
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
            }

            throw Exception("La cabecera no es igual")
        }

        throw Exception("El formato no es correcto")
    }


    /**
     * Leer j s o n
     *
     * @param ruta
     * @return List<ResiduosDTO>
     */
    fun leerJSON(ruta:String): List<ResiduosDTO>{
        logger.debug { "Leyendo archivo json" }
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".json")) {
            val json = Json { prettyPrint = true }
            return Json.decodeFromString(File(ruta).readText())
        }else {
            throw Exception("El formato no es correcto")
        }
    }

    /**
     * Leer x m l
     *
     * @param ruta
     * @return List<ResiduosDTO>
     */
    fun leerXML(ruta:String):List<ResiduosDTO>{
        logger.debug { "Leyendo archivo xml" }
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".xml")) {
            val xml = XML { indentString = " " }
            return XML.decodeFromString(fichero.readText())
        }else {
            throw Exception("El formato no es correcto")
        }
    }

    /**
     * Copiar c s v
     *
     * @param ruta
     * @param rutaDestino
     */
    fun copiarCSV(ruta: String, rutaDestino: String){
        logger.debug { "Copiando archivo csv" }
        val fichero = File(ruta+File.separator+"modelo_residuos_2021.csv")
        fichero.copyTo(File(rutaDestino+File.separator+"modelo_residuos_2021.csv"))
    }

    /**
     * Exportar c s v
     *
     * @param ruta
     * @param residuos
     */
    fun exportarCSV(ruta: String, residuos: List<ResiduosDTO>){
        logger.debug { "Exportando archivo csv" }
        val fichero = File(ruta+File.separator+"modelo_residuos_2021.csv")
        fichero.writeText("anio;mes;lote;residuos;distrito;nom_ditrito;toneladas\n")
        residuos.forEach { fichero.appendText("\n${it.anio};${it.mes};" +
                "${it.lote};${it.residuos};${it.distrito};${it.lote};${it.distrito};${it.nom_ditrito};" +
                "${it.toneladas}") }
    }

    /**
     * Exportar j s o n
     *
     * @param ruta
     * @param residuos
     */
    fun exportarJSON(ruta: String, residuos: List<ResiduosDTO>) {
        logger.debug { "Exportando archivo json" }
        val json= Json { prettyPrint=true }
        val fichero=File(ruta+File.separator+"modelo_residuos_2021.json")
        fichero.writeText(json.encodeToString(residuos))
    }


    /**
     * Exportar x m l
     *
     * @param ruta
     * @param residuos
     */
    fun exportarXML(ruta: String, residuos:List<ResiduosDTO>){
        logger.debug { "Exportando archivo xml" }
        val xml= XML{indentString=" "}
        val fichero=File(ruta+File.separator+"modelo_residuos_2021.xml")
        fichero.writeText(xml.encodeToString(residuos))
    }
}