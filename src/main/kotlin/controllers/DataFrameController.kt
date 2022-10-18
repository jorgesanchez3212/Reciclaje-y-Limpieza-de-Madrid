package controllers

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.label.xlab
import jetbrains.letsPlot.label.ylab
import jetbrains.letsPlot.letsPlot
import models.*
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.html
import utils.Html
import java.awt.Color
import java.io.File
import java.time.LocalDateTime
import java.util.*

import kotlin.system.measureTimeMillis

/**
 * Data frame Controller
 *
 * @property residuos
 * @property contenedores
 */

class DataFrameController(
    val residuos: List<Residuos>,
    val contenedores: List<Contenedores>
) {


    private var co: DataFrame<Contenedores>
    private var re: DataFrame<Residuos>


    init {
        re = residuos.toDataFrame()
        re.cast<Residuos>()
        co = contenedores.toDataFrame()
        co.cast<Contenedores>()
    }

    //Saber si el distrito existe o no
    fun distritoExiste(distrito: String): Boolean {
        var f = distrito.uppercase() in co.values()
        return f
    }
    //Resumen

    /**
     * Número de contenedores de cada tipo que hay en cada distrito
     *
     * @return String
     */
    fun numeroContenedoresTipoDistrito(): String {

        return co.groupBy("distrito", "tipoContenedor")
            .aggregate { count() into "contenedores" }
            .sortBy("distrito").html()

    }

    /**
     * Media de contenedores de cada tipo por distrito
     *
     * @return String
     */
    fun mediaContenedoresTipoDistrito(): String {
        return co.groupBy("distrito", "tipoContenedor")
            .aggregate { meanOf { sum("cantidad") } into "media" }
            .sortBy("distrito")
            .html()
    }

    /**
     * Gráfico con el total de contenedores por distrito
     *
     */
    fun graficoTotalContenedoresPorDistrito(rutaDestino: String) {
        var a = co.groupBy("distrito", "tipoContenedor")
            .aggregate { count() into "total" }
            .sortBy("distrito")
            .toMap()

        var fig: Plot = letsPlot(data = a) + geomBar(
            stat = Stat.identity,
            fill = Color.BLUE
        ) {
            x = "distrito"
            y = "total"
        } + labs(
            x = "Distrito",
            y = "total",
            title = "total de contenedores por distrito"
        )
        ggsave(
            fig,
            "totalContenedores.png",
            path = rutaDestino + File.separator + "graficoTotalContenedoresPorDistrito.png"
        )
    }

    /**
     * Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito
     *
     * @return String
     */
    fun mediaToneladasAnualesTipoBasuraDistrito(): String {
        return re.groupBy("nom_ditrito", "anio", "residuos")
            .aggregate {
                mean("toneladas") into "Media"
            }.sortBy("nom_ditrito")
            .html()
    }


    /**
     * Gráfico de media de toneladas mensuales de recogida de basura por distrito
     *
     */
    fun graficoToneladasMensualesPorDistrito(rutaDestino: String) {
        var a = re.groupBy("nom_ditrito", "mes")
            .aggregate { mean("toneladas") into "media" }
            .sortBy("nom_ditrito")
            .toMap()

        var fig: Plot = letsPlot(data = a) + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.GREEN
        ) {
            x = "nom_ditrito"
            y = "media"
        } + labs(
            x = "nom_ditrito",
            y = "media",
            title = "media de toneladas mensuales por mes "
        )

        ggsave(
            fig,
            "graficoToneladasMensualesPorDistrito.png",
            path = rutaDestino + File.separator + "graficoToneladasMensualesPorDistrito.png"
        )
    }


    /**
     * Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito
     *
     * @return String
     */
    fun maxMinMediaDesviacionToneladasAnualesTipoPorDistrito(): String {
        return re.groupBy("nom_ditrito", "residuos", "anio")
            .aggregate {
                max("toneladas") into "Maximo"
                min("toneladas") into "Minimo"
                mean("toneladas") into "Media"
                std("toneladas") into "Desviacion"
            }
            .sortBy("nom_ditrito")
            .html()
    }


    /**
     * Suma de todo lo recogido en un año por distrito
     *
     * @return String
     */
    fun sumaRecogidoAnualPorDistrito(): String {
        return re.groupBy("nom_ditrito", "anio")
            .aggregate { sum("toneladas") into "recogido" }
            .sortBy("nom_ditrito")
            .html()
    }


    /**
     * Por cada distrito obtener para cada tipo de residuo la cantidad recogida
     *
     * @return String
     */
    fun cantidadTipoPorDistrito(): String {
        return re.groupBy("nom_ditrito", "residuos")
            .aggregate { sum("toneladas") into "cantidad recogida" }
            .sortBy("nom_ditrito")
            .html()
    }


    //Resumen Distrito
    /**
     * Número de contenedores de cada tipo que hay en este distrito
     *
     * @param distrito
     * @return String
     */

    fun numeroContenedoresTipoPorDistrito(distrito: String): String {
        return co.groupBy("distrito", "tipoContenedor")
            .filter { it["distrito"].toString().uppercase() == distrito.uppercase() }
            .aggregate { count() into "numero_contenedores" }
            .html()
    }


    /**
     * Total de toneladas recogidas en ese distrito por residuo
     *
     * @param distrito
     * @return String
     */
    fun totalToneladasRecogidasDistrito(distrito: String): String {
        return re.groupBy("nom_ditrito", "residuos")
            .filter { it["nom_ditrito"].toString().uppercase() == distrito.uppercase() }
            .aggregate { sum("toneladas") into "total_toneladas" }
            .html()
    }


    /**
     * Gráfico con el total de toneladas por residuo en ese distrito
     *
     */
    fun graficaTotalToneladasPorResiduoDistrito(rutaDestino: String, distrito: String) {

        var a = re.groupBy("nom_ditrito", "residuos")
            .filter { it["nom_ditrito"].toString().uppercase() == distrito.uppercase() }
            .aggregate { sum("toneladas") into "total" }
            .toMap()

        var fig: Plot = letsPlot(data = a) + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.BLACK
        ) {
            x = "residuos"
            y = "total"
        } + labs(
            x = "residuos",
            y = "total",
            title = "Gráfico con el total de toneladas por residuo en ese distrito"
        )

        ggsave(
            fig,
            "graficaTotalToneladasPorResiduoDistrito.png",
            path = rutaDestino + File.separator + "graficaTotalToneladasPorResiduoDistrito.png"
        )

    }


    /**
     * Máximo, mínimo, media y desviación por mes por residuo en dicho distrito
     *
     * @param distrito
     * @return String
     */
    fun maxMinMediaDesviacionToneladasMesTipoPorDistrito(distrito: String): String {
        return re.groupBy("nom_ditrito", "mes", "residuos")
            .filter { it["nom_ditrito"].toString().uppercase() == distrito.uppercase() }
            .aggregate {
                max("toneladas") into "max"
                min("toneladas") into "min"
                mean("toneladas") into "media"
                std("toneladas") into "desviacion"
            }
            .html()
    }


    /**
     * Gráfica del máximo, mínimo y media por meses en dicho distrito
     *
     */
    fun graficaMaxMinMediaMesesDistrito(rutaDestino: String, distrito: String) {
        var a = re.groupBy("nom_ditrito", "mes")
            .filter { it["nom_ditrito"].toString().uppercase() == distrito.uppercase() }
            .aggregate {
                max("toneladas") into "max"
                min("toneladas") into "min"
                mean("toneladas") into "media"
            }.toMap()

        var fig: Plot = letsPlot(data = a) + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.BLACK
        ) {
            x = "mes"
            y = "max"
        } + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.RED
        ) {
            x = "mes"
            y = "min"
        } + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.BLUE
        ) {
            x = "mes"
            y = "media"
        } + labs(
            x = "Mes",
            y = "Max",
            title = "Gráfica del máximo, mínimo y media por meses en dicho distrito"
        )

        ggsave(
            fig,
            "graficaMaxMinMediaMesesDistrito.png",
            path = rutaDestino + File.separator + "graficaMaxMinMediaMesesDistrito.png"
        )

    }


    fun resumen(rutaDestino: String): String {
        var numContenedoresDistrito: String
        var mediaContenedorDistrito: String
        var mediaToneladasAnualesTipoBasuraDistrito: String
        var maxminavgDesviacionToneladasDistrito: String
        var sumRecogidoAnualDistrito: String
        var cantidadRecogidaTipoPorDistrito: String
        var total: String = totalToneladasRecogidasDistrito("Barajas")


        var tiempoGeneracion = measureTimeMillis {
            numContenedoresDistrito = numeroContenedoresTipoDistrito()
            mediaContenedorDistrito = mediaContenedoresTipoDistrito()
            graficoTotalContenedoresPorDistrito(rutaDestino)
            mediaToneladasAnualesTipoBasuraDistrito = mediaToneladasAnualesTipoBasuraDistrito()
            graficoToneladasMensualesPorDistrito(rutaDestino)
            maxminavgDesviacionToneladasDistrito = maxMinMediaDesviacionToneladasAnualesTipoPorDistrito()
            sumRecogidoAnualDistrito = sumaRecogidoAnualPorDistrito()
            cantidadRecogidaTipoPorDistrito = cantidadTipoPorDistrito()
        }

        var resumenHtml = Html(
            numContenedoresDistrito,
            mediaContenedorDistrito,
            mediaToneladasAnualesTipoBasuraDistrito,
            maxminavgDesviacionToneladasDistrito,
            sumRecogidoAnualDistrito,
            cantidadRecogidaTipoPorDistrito,
            tiempoGeneracion.toString(),
            "Barajas",
            LocalDateTime.now().toString(),
            total
        )
        return resumenHtml.directorioHtml()

    }

    fun resumenDistrito(rutaDestino: String, distrito: String): String {
        var numContenedoresDistrito: String
        var mediaContenedorDistrito: String = mediaContenedoresTipoDistrito()
        var mediaToneladasAnualesTipoBasuraDistrito: String = mediaToneladasAnualesTipoBasuraDistrito()
        var maxminavgDesviacionToneladasDistrito: String
        var sumRecogidoAnualDistrito: String = sumaRecogidoAnualPorDistrito()
        var cantidadRecogidaTipoPorDistrito: String = cantidadTipoPorDistrito()
        var total: String


        var tiempoGeneracion = measureTimeMillis {
            numContenedoresDistrito = numeroContenedoresTipoPorDistrito(distrito)
            total = totalToneladasRecogidasDistrito(distrito)
            graficaTotalToneladasPorResiduoDistrito(rutaDestino, distrito)
            maxminavgDesviacionToneladasDistrito = maxMinMediaDesviacionToneladasMesTipoPorDistrito(distrito)
            graficaMaxMinMediaMesesDistrito(rutaDestino, distrito)
        }

        var resumenHtml = Html(
            numContenedoresDistrito,
            mediaContenedorDistrito,
            mediaToneladasAnualesTipoBasuraDistrito,
            maxminavgDesviacionToneladasDistrito,
            sumRecogidoAnualDistrito,
            cantidadRecogidaTipoPorDistrito,
            tiempoGeneracion.toString(),
            distrito,
            LocalDateTime.now().toString(),
            total
        )
        return resumenHtml.distrito()

    }
}