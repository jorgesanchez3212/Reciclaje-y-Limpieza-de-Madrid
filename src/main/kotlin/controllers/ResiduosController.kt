package controllers

import mappers.MapperContenedor
import mappers.MapperResiduos
import utils.Utils
import java.io.File
import java.util.*

class ResiduosController {


    fun parser(directorioOrigen: String, directorioDestino: String){
        val contenedorMapper= MapperContenedor()
        val residuosMapper= MapperResiduos()


        var a = residuosMapper.leerCSV(directorioOrigen + File.separator + "modelo_residuos_2021.csv")
        var b = contenedorMapper.leerCSV(directorioOrigen + File.separator + "contenedores_varios.csv")

        contenedorMapper.copiarCSV(directorioOrigen,directorioDestino)
        contenedorMapper.exportarJSON(directorioDestino,b)
        contenedorMapper.exportarXML(directorioDestino,b)

        residuosMapper.copiarCSV(directorioOrigen,directorioDestino)
        residuosMapper.exportarJSON(directorioDestino,a)
        residuosMapper.exportarXML(directorioDestino,a)
    }

    fun resumen(directorioOrigen: String, directorioDestino: String){

    }

    fun resumenD(distrito: String ,directorioOrigen: String, directorioDestino: String){

    }
}