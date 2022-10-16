package controllers

import dto.ContenedorDTO
import dto.ResiduosDTO
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.label.xlab
import jetbrains.letsPlot.label.ylab
import jetbrains.letsPlot.letsPlot
import models.Contenedores
import models.Residuos
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.html
import utils.Html
import java.awt.Color
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

    private var co : DataFrame<Contenedores>
    private var re : DataFrame<Residuos>


    init {
        re = residuos.toDataFrame()
        re.cast<Residuos>()
        co = contenedores.toDataFrame()
        co.cast<Contenedores>()
    }



    //Resumen

    /**
     * Número de contenedores de cada tipo que hay en cada distrito
     *
     * @return String
     */
    fun numeroContenedoresTipoDistrito() : String{
        return co.groupBy("distrito","tipoContenedor")
            .aggregate { count() into "total" }.sortBy("distrito").html()

    }

    /**
     * Media de contenedores de cada tipo por distrito
     *
     * @return String
     */
    fun mediaContenedoresTipoDistrito() : String{
       return co.groupBy("distrito","tipoContenedor")
            .aggregate { mean("cantidad") into "media de Contenedores" }
            .sortBy("distrito")
            .html()
    }

    /**
     * Gráfico con el total de contenedores por distrito
     *
     */
    fun graficoTotalContenedoresPorDistrito(){
        var totalContenedorDistrit = co.groupBy("distrito")
            .aggregate {
                count() into "total"
            }.toMap()
        var fig: Plot = letsPlot(data = totalContenedorDistrit.toMap()) + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.BLUE
        ) {
            x = "distrito"
            y = "total"
        } + labs(
            x = "Distritos",
            y = "Total",
            title = "Total de Contenedores por Distrito"
        )
        ggsave(fig, "totalContenedoresDistrito.png")
    }

    /**
     * Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito
     *
     * @return String
     */
    fun mediaToneladasAnualesTipoBasuraDistrito(): String{
        return  re.groupBy("nom_ditrito","toneladas","anio")
            .aggregate { mean("toneladas") into "media" }
            .sortBy("nom_ditrito")
            .html()
    }


    /**
     * Gráfico de media de toneladas mensuales de recogida de basura por distrito
     *
     */
    fun graficoToneladasMensualesPorDistrito(){
        var mediaToneladas = re.groupBy("nom_ditrito", "toneladas", "mes")
            .aggregate { mean("toneladas") into "media" }
            .sortBy("nom_ditrito")
        var fig = letsPlot(data = mediaToneladas.toMap()) + geomBar(
            stat = Stat.identity,
            alpha = 0.8
        ) {
            x = "mes"; y = "toneladas"
        } + xlab("mes"); ylab("toneladas") + ggtitle("media de toneladas mensuales de recogida de basura")
        ggsave(fig, "graficoToneladasDistrito.png")
    }


    /**
     * Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito
     *
     * @return String
     */
    fun maxMinMediaDesviacionToneladasAnualesTipoPorDistrito() : String{
        return re.groupBy("nom_ditrito","toneladas","anio")
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
    fun sumaRecogidoAnualPorDistrito():String{
        return re.groupBy("nom_ditrito", "anio")
            .aggregate {
                sum("toneladas") into "Suma Toneladas"
            }
            .sortBy("nom_ditrito")
            .html()
    }


    /**
     * Por cada distrito obtener para cada tipo de residuo la cantidad recogida
     *
     * @return String
     */
    fun cantidadTipoPorDistrito():String{
        return re.groupBy("nom_ditrito","residuos")
            .aggregate {
                sum("toneladas") into "Suma Toneladas"
            }
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

    fun numeroContenedoresTipoDistrito(distrito: String): String{
        return co.filter { it["distrito"] == distrito.uppercase(Locale.getDefault())}
            .groupBy("distrito","tipoContenedor")
            .aggregate { count() into "Total Contenedores" }
            .html()
    }


    /**
     * Total de toneladas recogidas en ese distrito
     *
     * @param distrito
     * @return String
     */
    fun totalToneladasRecogidasDistrito(distrito: String): String{
        return    re.filter { it["nom_ditrito"] == distrito}
            .groupBy("nom_ditrito","residuos")
            .aggregate { sum("toneladas") into "Suma de Toneladas" }
            .html()
    }


    /**
     * Gráfico con el total de toneladas por residuo en ese distrito
     *
     */
    fun graficaTotalToneladasPorResiduoDistrito(distrito:String) {
        val tonelada = re.filter { it["nom_ditrito"] == distrito }
            .groupBy("nom_ditrito", "residuos")
            .aggregate { sum("toneladas") into "Suma de Toneladas" }
        var fig:Plot= letsPlot(data = tonelada.toMap())+geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.RED,
            color = Color.GREEN
        ){
            x="nom_ditrito"
            y="Suma de Toneladas"
        }+ labs(
            x = "tonelada",
            y="mes",
            title = "Media de Toneladas de Residuo por Distrito"
        )
        ggsave(fig, "graficaToneladasResiduoDistrito.png")

    }


    /**
     * Máximo, mínimo, media y desviación por mes por residuo en dicho distrito
     *
     * @param distrito
     * @return String
     */
    fun maxMinMediaDesviacionToneladasMesTipoPorDistrito(distrito: String):String{
        return re.filter { it["nom_ditrito"]==distrito }
            .groupBy("nom_ditrito","residuos","mes")
            .aggregate {
                max("toneladas") into "Maximo"
                min("toneladas") into "Minimo"
                mean("toneladas") into "Media"
                std("toneladas") into "Desviacion"
            }
            .html()
    }


    /**
     * Gráfica del máximo, mínimo y media por meses en dicho distrito
     *
     */
    fun graficaMaxMinMediaMesesDistrito(distrito:String){


    }


    fun resumen() : String{
        var numContenedoresDistrito : String
        var mediaContenedorDistrito: String
        var mediaToneladasAnualesTipoBasuraDistrito: String
        var maxminavgDesviacionToneladasDistrito: String
        var sumRecogidoAnualDistrito: String
        var cantidadRecogidaTipoPorDistrito : String
        var total: String = totalToneladasRecogidasDistrito("Barajas")


        var tiempoGeneracion = measureTimeMillis {
            numContenedoresDistrito = numeroContenedoresTipoDistrito()
            mediaContenedorDistrito = mediaContenedoresTipoDistrito()
            graficoTotalContenedoresPorDistrito()
            mediaToneladasAnualesTipoBasuraDistrito = mediaToneladasAnualesTipoBasuraDistrito()
            graficoToneladasMensualesPorDistrito()
            maxminavgDesviacionToneladasDistrito = maxMinMediaDesviacionToneladasAnualesTipoPorDistrito()
            sumRecogidoAnualDistrito = sumaRecogidoAnualPorDistrito()
            cantidadRecogidaTipoPorDistrito = cantidadTipoPorDistrito()
        }

        var resumenHtml =  Html(
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

    fun resumenDistrito(distrito:String) : String{
        var numContenedoresDistrito : String
        var mediaContenedorDistrito: String = mediaContenedoresTipoDistrito()
        var mediaToneladasAnualesTipoBasuraDistrito: String = mediaToneladasAnualesTipoBasuraDistrito()
        var maxminavgDesviacionToneladasDistrito: String
        var sumRecogidoAnualDistrito: String = sumaRecogidoAnualPorDistrito()
        var cantidadRecogidaTipoPorDistrito : String = cantidadTipoPorDistrito()
        var total: String


        var tiempoGeneracion = measureTimeMillis {
            numContenedoresDistrito = numeroContenedoresTipoDistrito(distrito)
            total = totalToneladasRecogidasDistrito(distrito)
            graficaTotalToneladasPorResiduoDistrito(distrito)
            maxminavgDesviacionToneladasDistrito = maxMinMediaDesviacionToneladasMesTipoPorDistrito(distrito)
            graficaMaxMinMediaMesesDistrito(distrito)
        }

        var resumenHtml =  Html(
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