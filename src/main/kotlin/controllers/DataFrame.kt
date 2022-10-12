package controllers

import mappers.MapperContenedor
import mappers.MapperResiduos
import models.Contenedores
import models.Residuos


import org.jetbrains.kotlinx.dataframe.api.*
import java.io.File

class DataFrame {
    fun procesarDataFramesResiduos() {
        println("Residuos con Data Frame")
        println("======================")
        val residuos by lazy { MapperResiduos().leerCSV("data/modelo_residuos_2021.csv")}
        val contenedores by lazy { MapperContenedor().leerCSV("data/contenedores_varios.csv") }
//Prueba residuos
        val re by lazy { residuos.toDataFrame() }
        re.cast<Residuos>()

        println("Esquema del DataFrame")
        println(re.schema())
        println(re.head(5))
        println("Numero de filas: ${re.rowsCount()}")
        re.select("distrito").print(10)
//Prueba contenedores
        val container by lazy { contenedores.toDataFrame() }
        container.cast<Contenedores>()

        println("Esquema del DataFrame")
        println(container.schema())
        println(container.head(5))
        println("Numero de filas: ${container.rowsCount()}")
        container.select("distrito").print(10)
        //Consultas residuos

        val consultaCantidadResiduoDistrito = re.groupBy("nom_ditrito", "residuos")
            .aggregate { sum("toneladas") into "total_recogido" }

        println("Por cada distrito obtener para cada tipo de residuo la cantidad recogida:  ")
        println(consultaCantidadResiduoDistrito)

        val maxMinMediaTonAnua = re.groupBy("residuos", "nom_ditrito", "toneladas")
            .aggregate {
                max("toneladas") into "max"
                min("toneladas") into "min"
                mean("toneladas") into "media"
                std("toneladas") into "desviacion"
            }
        println("Máximo, mínimo y media")
        println(maxMinMediaTonAnua)

        val sumaAnioRecogidoPorDistrito = re.groupBy("nom_ditrito", "anio").aggregate {
            sum("toneladas") into "suma"
        }
        println("Suma de lo recogido en un año por cada distrito")
        println(sumaAnioRecogidoPorDistrito)


        //Consultas contenedores

        val contenedoresTipoPorDistritio = container.groupBy("distrito","tipoContenedor")
            .aggregate { sum("cantidad") into "total" }
        println("contenedores de cada tipo que hay en cada distrito")
        println(contenedoresTipoPorDistritio)

    }

}