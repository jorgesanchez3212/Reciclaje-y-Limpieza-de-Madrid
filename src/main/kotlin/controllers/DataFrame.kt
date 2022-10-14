package controllers

import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.Stat.identity
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.letsPlot
import mappers.MapperContenedor
import mappers.MapperResiduos
import models.Contenedores
import models.Residuos


import org.jetbrains.kotlinx.dataframe.api.*
import java.io.File
import java.util.*

class DataFrame {

    fun resumen(rutaOrigenResiduos: String,rutaOrigenContenedores: String){
        val tiempoInicial = System.currentTimeMillis()

        val residuos by lazy{MapperResiduos().leerCSV(rutaOrigenResiduos)}
        val contenedores by lazy{MapperContenedor().leerCSV(rutaOrigenContenedores)}

        val re by lazy { residuos.toDataFrame() }
        val co by lazy { contenedores.toDataFrame() }
        re.cast<Residuos>()
        co.cast<Contenedores>()
        println("Número de contenedores de cada tipo que hay en cada distrito")
        //Número de contenedores de cada tipo que hay en cada distrito
        co.groupBy("distrito","tipoContenedor")
            .aggregate { count() into "total" }.sortBy("distrito").print()

        //Media de contenedores de cada tipo por distrito

        println("Media de contenedores de cada tipo por distrito")


        co.groupBy("distrito","tipoContenedor")
            .aggregate { mean("cantidad") into "media de Contenedores" }
            .sortBy("distrito")
            .print()


        //Gráfico con el total de contenedores por distrito




        //Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito
        re.groupBy("nom_ditrito","toneladas","anio")
            .aggregate { mean("toneladas") into "media" }
            .sortBy("nom_ditrito")
            .print()

        //Gráfico de media de toneladas mensuales de recogida de basura por distrito




        //Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
        // de basura agrupadas por distrito
        re.groupBy("nom_ditrito","toneladas","anio")
            .aggregate {
                max("toneladas") into "Maximo"
                min("toneladas") into "Minimo"
                mean("toneladas") into "Media"
                std("toneladas") into "Desviacion"
            }
            .sortBy("nom_ditrito")
            .print()

        // Suma de todo lo recogido en un año por distrito
        re.groupBy("nom_ditrito", "anio")
            .aggregate {
                sum("toneladas") into "Suma Toneladas"
            }
            .sortBy("nom_ditrito")
            .print()

        //Por cada distrito obtener para cada tipo de residuo la cantidad recogida
        re.groupBy("nom_ditrito","residuos")
            .aggregate {
                sum("toneladas") into "Suma Toneladas"
            }
            .sortBy("nom_ditrito")
            .print()

        //Tiempo de generación del mismo en milisegundos
        val tiempoFinal = System.currentTimeMillis()
        println("Tiempo final es ${(tiempoFinal-tiempoInicial)/1000} milisegundos")

    }



    fun resumenDistrito(distrito: String,rutaOrigenResiduos: String,rutaOrigenContenedores: String){
        val tiempoInicial = System.currentTimeMillis()
        val residuos by lazy{MapperResiduos().leerCSV(rutaOrigenResiduos)}
        val contenedores by lazy{MapperContenedor().leerCSV(rutaOrigenContenedores)}

        val re by lazy { residuos.toDataFrame() }
        val co by lazy { contenedores.toDataFrame() }
        re.cast<Residuos>()
        co.cast<Contenedores>()
        println("Número de contenedores de cada tipo que hay en este distrito")
        //Número de contenedores de cada tipo que hay en este distrito
        co.filter { it["distrito"] == distrito.uppercase(Locale.getDefault())}
            .groupBy("distrito","tipoContenedor")
            .aggregate { count() into "Total Contenedores" }
            .print()
        println("Total de toneladas recogidas en ese distrito")
        //Total de toneladas recogidas en ese distrito
        re.filter { it["nom_ditrito"] == distrito}
            .groupBy("nom_ditrito","residuos")
            .aggregate { sum("toneladas") into "Suma de Toneladas" }
            .print()


        //Gráfico con el total de toneladas por residuo en ese distrito






        //Máximo, mínimo, media y desviación por mes por residuo en dicho distrito
        re.filter { it["nom_ditrito"]==distrito }
            .groupBy("nom_ditrito","residuos","mes")
            .aggregate {
                max("toneladas") into "Maximo"
                min("toneladas") into "Minimo"
                mean("toneladas") into "Media"
                std("toneladas") into "Desviacion"
            }
            .print()



        //Gráfica del máximo, mínimo y media por meses en dicho distrito



        //Tiempo de generación del mismo en milisegundos
        val tiempoFinal = System.currentTimeMillis()
        println("Tiempo final es ${(tiempoFinal-tiempoInicial)/1000} milisegundos")
    }




}