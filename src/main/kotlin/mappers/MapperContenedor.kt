package mappers


import dto.ContenedorDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Contenedores
import mu.KotlinLogging
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File

/**
 * Mapper contenedor
 * @author Jorge y Alfredo
 * @since 19/10/2022
 * @constructor Create empty Mapper contenedor
 */
class MapperContenedor () {


    val logger = KotlinLogging.logger{}

    /**
     * To contenedor d t o
     *
     * @param contenedores
     * @return ContenedorDTO
     */
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

    /**
     * To contenedores
     *
     * @param contenedores
     * @return Contenedor
     */
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

    /**
     * Leer c s v
     *
     * @param ruta
     * @return List<ContenedorDTO>
     */
    fun leerCSV(ruta: String): List<ContenedorDTO> {
        logger.debug{"Leyendo archivo csv"}
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".csv")) {
            if(fichero.readLines().take(1).first().split(";").size == 16) {
                return fichero.readLines()
                    .drop(1)
                    .map { contenedores -> contenedores.split(";") }
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
            }else{
                val f = fichero.readLines().first()
                println(f)
                throw Exception("La cabecera no es igual")
            }
        }else {
            throw Exception("El formato no es correcto")
        }
    }

    /**
     * Leer j s o n
     *
     * @param ruta
     * @return List<ContenedorDTO>
     */
    fun leerJSON(ruta:String): List<ContenedorDTO>{
        logger.debug{"Leyendo archivo json"}
        val ficheroJson = File(ruta)
        if(ficheroJson.exists()&&ruta.endsWith(".json")) {
            val json = Json { prettyPrint = true }
            return Json.decodeFromString(ficheroJson.readText())
        }
        throw Exception("El formato no es correcto")
    }

    /**
     * Leer x m l
     *
     * @param ruta
     * @return List<ContenedorDTO>
     */
    fun leerXML(ruta:String):List<ContenedorDTO>{
        logger.debug { "Leyendo archivo xml" }
        val fichero = File(ruta)
        if(fichero.exists()&&ruta.endsWith(".xml")) {
            val xml = XML { indentString = " " }
            return XML.decodeFromString(fichero.readText())
        }
        throw Exception("El formato no es correcto")
    }

    /**
     * Copiar c s v
     *
     * @param ruta
     * @param rutaDestino
     */
    fun copiarCSV(ruta: String, rutaDestino: String){
        logger.debug { "Copiando archivo csv" }
        val fichero = File(ruta+File.separator+"contenedores_varios.csv")
        fichero.copyTo(File(rutaDestino + File.separator + "contenedores_varios.csv"))
    }

    /**
     * Exportar c s v
     *
     * @param ruta
     * @param contenedores
     */
    fun exportarCSV(ruta: String, contenedores:  List<ContenedorDTO>){
        logger.debug { "Exportando archivo csv" }
        val fichero = File(ruta+File.separator+"contenedores_varios.csv")
        fichero.writeText("codInterno;lote;tipoContendor;modelo;descripcionModelo;cantidad;lote;distrito;barrio;tipoVia;nombre" +
                ";numero;cordenadax;cordenaday;longitud;latitud;direccion\n")
        contenedores.forEach { fichero.appendText("\n${it.codInterno};${it.tipoContenedor};" +
                "${it.modelo};${it.descripcionModelo};${it.cantidad};${it.lote};${it.distrito};${it.barrio};" +
                "${it.tipoVia};${it.nombre};${it.numero};${it.cordenadax};${it.cordenaday};${it.longitud};" +
                "${it.latitud};${it.direccion}") }

    }

    /**
     * Exportar j s o n
     *
     * @param ruta
     * @param contenedores
     */
    fun exportarJSON(ruta: String, contenedores: List<ContenedorDTO>) {
        logger.debug { "Exportando archivo json" }
        val json = Json { prettyPrint = true }
        val fichero = File(ruta + File.separator + "contenedores_varios.json")
        fichero.writeText(json.encodeToString(contenedores))
    }

    /**
     * Exportar x m l
     *
     * @param ruta
     * @param contenedores
     */
    fun exportarXML(ruta: String, contenedores: List<ContenedorDTO>) {
        logger.debug { "Exportando archivo xml" }
        val xml = XML { indentString = " " }
        val fichero = File(ruta + File.separator + "contenedores_varios.xml")
        fichero.writeText(xml.encodeToString(contenedores))
    }
}
