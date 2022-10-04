package utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import models.Contenedores
import org.apache.poi.ss.formula.functions.T
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class Utils {
    fun copiarFicheroCSV(directorioOrigen: String,directorioDestino: String){
        val fichero = File(directorioOrigen)
        val rutaDestino = File(directorioDestino)
        if(fichero.exists()){
            File(directorioOrigen).copyTo(rutaDestino)
        }else{
            println("El fichero no existe")
        }
    }

    fun exportarJSONContenedores(csv: List<Contenedores>,directorioDestino: String){

        val path = directorioDestino+File.separator+"contenedores.json"

        PrintWriter(FileWriter(path)).use {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonMiListaPretty :String = gsonPretty.toJson(csv)
            it.write(jsonMiListaPretty)
        }





    }
}