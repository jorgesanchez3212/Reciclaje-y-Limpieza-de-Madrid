package controllers

import mappers.MapperContenedor
import mappers.MapperResiduos
import models.Bitacora
import mu.KotlinLogging
import utils.Html
import java.io.File
import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Residuos controller
 * @author Jorge y Alfredo
 * @since 19/10/2022
 */
class ResiduosController {

    /**
     * Parser
     *
     * @param directorioOrigen
     * @param directorioDestino
     */

    val logger = KotlinLogging.logger{}
    fun parser(directorioOrigen: String, directorioDestino: String) {
        val contenedorMapper = MapperContenedor()
        val residuosMapper = MapperResiduos()
        logger.debug { "Se ejecuta la opcion parser" }
        var tiempo = measureTimeMillis {
            var a = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
            var b = contenedorMapper.leerCSV(directorioOrigen + File.separator + "contenedores_varios.csv")
            logger.debug{"Copia el csv de contenedores en el directorio destino"}
            contenedorMapper.copiarCSV(directorioOrigen, directorioDestino)
            logger.debug{"Exporta el json de contenedores en el directorio destino"}
            contenedorMapper.exportarJSON(directorioDestino, b)
            logger.debug{"Exporta el xml de contenedores en el directorio destino"}
            contenedorMapper.exportarXML(directorioDestino, b)
            logger.debug{"Copia el csv de residuos en el directorio destino"}
            residuosMapper.copiarCSV(directorioOrigen, directorioDestino)

            logger.debug{"Exporta el json de residuos en el directorio destino"}
            residuosMapper.exportarJSON(directorioDestino, a)
            logger.debug{"Exporta el xml de residuos en el directorio destino"}
            residuosMapper.exportarXML(directorioDestino, a)
        }

        var id = UUID.randomUUID().toString()
        var now = LocalDateTime.now().toString()

        Bitacora(
            id,
            now,
            "parser",
            true,
            tiempo,
            directorioDestino
        ).bitacora()

        logger.debug{"Crea el bitacora"}
    }

    /**
     * Resumen
     *
     * @param directorioOrigen
     * @param directorioDestino
     */
    fun resumen(directorioOrigen: String, directorioDestino: String) {
        val contenedorMapper = MapperContenedor()
        val residuosMapper = MapperResiduos()
        logger.debug { "Se ejecuta la opcion resumen" }
        var tiempo = measureTimeMillis {
        val contenedoresCSV = File(directorioOrigen + File.separator + "contenedores_varios.csv")
        val contendorJSON = File(directorioOrigen + File.separator + "contenedores_varios.json")
        val contendorXML = File(directorioOrigen + File.separator + "contenedores_varios.xml")

        val residuosCSV = File(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
        val residuosJson = File(directorioOrigen + File.separator + "modelo_residuos_2021.json")
        val residuosXml = File(directorioOrigen + File.separator + "modelo_residuos_2021.xml")

        if (contenedoresCSV.exists()) {
            val a = contenedorMapper.leerCSV(directorioOrigen + File.separator + "contenedores_varios.csv")
                .map { contenedorMapper.toContenedores(it) }.toList()

            if (residuosCSV.exists()) {
                val b = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)

                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }


            } else if (residuosJson.exists()) {
                val b = residuosMapper.leerJSON(directorioOrigen + File.separator + "modelo_residuos_2021.json")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            } else if (residuosXml.exists()) {
                val b = residuosMapper.leerXML(directorioOrigen + File.separator + "modelo_residuos_2021.xml")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            }


        } else if (contendorJSON.exists()) {
            val a = contenedorMapper.leerJSON(directorioOrigen + File.separator + "contenedores_varios.json")
                .map { contenedorMapper.toContenedores(it) }.toList()
            if (residuosCSV.exists()) {
                val b = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            } else if (residuosJson.exists()) {
                val b = residuosMapper.leerJSON(directorioOrigen + File.separator + "modelo_residuos_2021.json")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            } else if (residuosXml.exists()) {
                val b = residuosMapper.leerXML(directorioOrigen + File.separator + "modelo_residuos_2021.xml")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            }


        } else if (contendorXML.exists()) {
            val a = contenedorMapper.leerXML(directorioOrigen + File.separator + "contenedores_varios.xml")
                .map { contenedorMapper.toContenedores(it) }.toList()
            if (residuosCSV.exists()) {
                val b = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            } else if (residuosJson.exists()) {
                val b = residuosMapper.leerJSON(directorioOrigen + File.separator + "modelo_residuos_2021.json")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            } else if (residuosXml.exists()) {
                val b = residuosMapper.leerXML(directorioOrigen + File.separator + "modelo_residuos_2021.xml")
                    .map { residuosMapper.toResiduos(it) }.toList()
                val df = DataFrameController(b, a)
                val c = df.resumen(directorioDestino)
                val fichero = File(directorioDestino + File.separator + "resumen.html")
                fichero.createNewFile()
                fichero.writeText(c)

                val ficheroCSS = File(directorioDestino + File.separator + "style.css")
                if (ficheroCSS.exists()){

                }else{
                    val cc = df.css()
                    ficheroCSS.createNewFile()
                    ficheroCSS.writeText(cc)
                }
            }

            } else {
                throw Exception("Fichero no Admitido")

                System.exit(4)
            }
        }
        var id = UUID.randomUUID().toString()
        var now = LocalDateTime.now().toString()

        Bitacora(
            id,
            now,
            "resumen",
            true,
            tiempo,
            directorioDestino
        ).bitacora()
        logger.debug{"Crea el bitacora"}
    }


    /**
     * Resumen distrito
     *
     * @param distrito
     * @param directorioOrigen
     * @param directorioDestino
     */
    fun resumenDistrito(distrito: String, directorioOrigen: String, directorioDestino: String) {
        val contenedorMapper = MapperContenedor()
        val residuosMapper = MapperResiduos()
        logger.debug { "Se ejecuta la opcion resumen distrito"}

        var tiempo= measureTimeMillis {
            val contenedoresCSV = File(directorioOrigen + File.separator + "contenedores_varios.csv")
            val contendorJSON = File(directorioOrigen + File.separator + "contenedores_varios.json")
            val contendorXML = File(directorioOrigen + File.separator + "contenedores_varios.xml")

            val residuosCSV = File(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
            val residuosJson = File(directorioOrigen + File.separator + "modelo_residuos_2021.json")
            val residuosXml = File(directorioOrigen + File.separator + "modelo_residuos_2021.xml")

            if (contenedoresCSV.exists()) {
                val a = contenedorMapper.leerCSV(directorioOrigen + File.separator + "contenedores_varios.csv")
                    .map { contenedorMapper.toContenedores(it) }.toList()

                if (residuosCSV.exists()) {
                    val b = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
                        .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)


                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }

                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)

                } else if (residuosJson.exists()) {
                    val b = residuosMapper.leerJSON(directorioOrigen + File.separator + "modelo_residuos_2021.json")
                        .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }


                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                } else if (residuosXml.exists()) {
                    val b = residuosMapper.leerXML(directorioOrigen + File.separator + "modelo_residuos_2021.xml")
                        .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }


                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                }

            } else if (contendorJSON.exists()) {
                val a =
                    contenedorMapper.leerJSON(directorioOrigen + File.separator + "contenedores_varios.json")
                        .map { contenedorMapper.toContenedores(it) }.toList()
                if (residuosCSV.exists()) {
                    val b =
                        residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
                            .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }


                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                } else if (residuosJson.exists()) {
                    val b =
                        residuosMapper.leerJSON(directorioOrigen + File.separator + "modelo_residuos_2021.json")
                            .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }


                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                } else if (residuosXml.exists()) {
                    val b =
                        residuosMapper.leerXML(directorioOrigen + File.separator + "modelo_residuos_2021.xml")
                            .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }


                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                }

            } else if (contendorXML.exists()) {
                val a =
                    contenedorMapper.leerXML(directorioOrigen + File.separator + "contenedores_varios.xml")
                        .map { contenedorMapper.toContenedores(it) }.toList()
                if (residuosCSV.exists()) {
                    val b =
                        residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
                            .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)

                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }

                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                } else if (residuosJson.exists()) {
                    val b =
                        residuosMapper.leerJSON(directorioOrigen + File.separator + "modelo_residuos_2021.json")
                            .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }

                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                } else if (residuosXml.exists()) {
                    val b =
                        residuosMapper.leerXML(directorioOrigen + File.separator + "modelo_residuos_2021.xml")
                            .map { residuosMapper.toResiduos(it) }.toList()
                    val df = DataFrameController(b, a)
                    if (df.distritoExiste(distrito) == false) {
                        throw Exception("Distrito no existe")
                        System.exit(32)
                    }


                    val c = df.resumenDistrito(directorioDestino, distrito)
                    val fichero = File(directorioDestino + File.separator + "resumenDistrito.html")
                    fichero.createNewFile()
                    fichero.writeText(c)
                } else {
                    throw Exception("Fichero no Admitido")
                    System.exit(4)
                }

            }
        }

        var id = UUID.randomUUID().toString()
        var now = LocalDateTime.now().toString()

        Bitacora(
            id,
            now,
            "resumen distrito",
            true,
            tiempo,
            directorioDestino
        ).bitacora()
        logger.debug { "Se crea el Bitacora" }
    }
}