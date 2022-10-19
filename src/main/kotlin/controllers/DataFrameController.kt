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
import mu.KotlinLogging
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
 *@author Jorge y Alfredo
 * @since 19/10/2022
 * @property residuos
 * @property contenedores
 */

class DataFrameController(
    val residuos: List<Residuos>,
    val contenedores: List<Contenedores>
) {

 val logger = KotlinLogging.logger{}
    private var co: DataFrame<Contenedores>
    private var re: DataFrame<Residuos>


    init {
        re = residuos.toDataFrame()
        re.cast<Residuos>()
        co = contenedores.toDataFrame()
        co.cast<Contenedores>()
    }


    fun distritoExiste(distrito: String): Boolean {
        logger.debug { "Comprobacion si el distrito existe" }
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
        logger.debug("Número de contenedores de cada tipo que hay en cada distrito")
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
        logger.debug{"Media de contenedores de cada tipo por distrito"}
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
        logger.debug{"Gráfico con el total de contenedores por distrito"}
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
        logger.debug{"Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito"}
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
        logger.debug{"Gráfico de media de toneladas mensuales de recogida de basura por distrito"}
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
        logger.debug{"Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito"}
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
        logger.debug { "Suma de todo lo recogido en un año por distrito" }
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
        logger.debug{"Por cada distrito obtener para cada tipo de residuo la cantidad recogida"}
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
        logger.debug{"Número de contenedores de cada tipo que hay en este distrito"}
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
        logger.debug{"Total de toneladas recogidas en ese distrito por residuo"}
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
        logger.debug{"Gráfico con el total de toneladas por residuo en ese distrito"}
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
        logger.debug{"Máximo, mínimo, media y desviación por mes por residuo en dicho distrito"}
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

        logger.debug { "Gráfica del máximo, mínimo y media por meses en dicho distrito" }
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
        logger.debug { "Html que genera de resumen" }
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
        return resumenHtml.directorioHtml(rutaDestino)

    }

    fun resumenDistrito(rutaDestino: String, distrito: String): String {
        logger.debug{"Html que genera de la opcion resumen distrito"}
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
        return resumenHtml.distrito(rutaDestino)

    }

    fun css() : String{
        return """.id{
    background-color: rgb(17, 14, 204);
    width: 1187px;
    height: 50px;
    text-transform: uppercase;
    text-align: center;
    font-size: 15px;
    color: aliceblue;
    padding:55px;
    border: 2px solid black;


}

.bodyy{
    background-color: rgb(218, 253, 18);  
    width: 1287px;
    height: 6200px;
    padding-left: 8px;
    padding-top: 8px;
    float: left;
    border: 2px solid black;
    
}""".trimIndent()

    }
}