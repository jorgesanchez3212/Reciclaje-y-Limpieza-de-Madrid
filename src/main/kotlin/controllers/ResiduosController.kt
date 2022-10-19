package controllers

import mappers.MapperContenedor
import mappers.MapperResiduos
import models.Bitacora
import utils.Html
import java.io.File
import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

class ResiduosController {


    fun parser(directorioOrigen: String, directorioDestino: String) {
        val contenedorMapper = MapperContenedor()
        val residuosMapper = MapperResiduos()

        var tiempo = measureTimeMillis {
            var a = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
            var b = contenedorMapper.leerCSV(directorioOrigen + File.separator + "contenedores_varios.csv")

            contenedorMapper.copiarCSV(directorioOrigen, directorioDestino)
            contenedorMapper.exportarJSON(directorioDestino, b)
            contenedorMapper.exportarXML(directorioDestino, b)

            residuosMapper.copiarCSV(directorioOrigen, directorioDestino)
            residuosMapper.exportarJSON(directorioDestino, a)
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
    }

    fun resumen(directorioOrigen: String, directorioDestino: String) {
        val contenedorMapper = MapperContenedor()
        val residuosMapper = MapperResiduos()

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
    }



    fun resumenDistrito(distrito: String, directorioOrigen: String, directorioDestino: String) {
        val contenedorMapper = MapperContenedor()
        val residuosMapper = MapperResiduos()

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
    }
}