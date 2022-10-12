package utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import models.Contenedores
import org.apache.poi.ss.formula.functions.T
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class Utils {
    fun comprobarExtension(rutaOrigen: String): Boolean {
        return rutaOrigen.endsWith(".json") || rutaOrigen.endsWith(".csv") || rutaOrigen.endsWith(".xml")
    }

    fun comprobarRuta(rutaOrigen: String, rutaFinal: String): Boolean {
        return File(rutaOrigen).exists() && File(rutaFinal).exists()
    }

}