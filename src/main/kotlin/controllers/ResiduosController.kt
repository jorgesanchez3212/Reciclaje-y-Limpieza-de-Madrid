package controllers

import utils.Utils
import java.util.*

class ResiduosController {
    fun ejecutar(args: Array<String>): String {
        if (args.size < 2 || args.size >= 5) {
            throw Exception("Argumentos Incorrectos")
        }
        val argMax = args.size
        if (args[0].lowercase(Locale.getDefault()) == "parser") {
            val rutaOrigen = args[1]
            val rutaFinal = args[2]
            if (Utils().comprobarExtension(rutaOrigen)) {
                return "Parsear"
            }
        } else if (args[0].lowercase(Locale.getDefault()) == "resumen" && args.size == 3) {
            val rutaOrigen = args[1]
            val rutaFinal = args[2]
            if (Utils().comprobarRuta(rutaOrigen, rutaFinal)){ //&& validarExtension(pathOrigen)) {
                return "Resumen"
            } else {
                throw Exception("Extensión no válida")
            }
        } else if (args[0].lowercase(Locale.getDefault()) == "resumen" && args[1].lowercase(Locale.getDefault()) == "distrito") {
            val rutaOrigen = args[2]
            val rutaFinal = args[3]
            if (Utils().comprobarRuta(rutaOrigen, rutaFinal) && Utils().comprobarExtension(rutaOrigen)) {
                return "ResumenDistrito"
            } else {
                throw Exception("Extensión no válida")
            }
        }
        throw Exception("Argumentos no válidos")
    }

    fun parser(directorioOrigen: String, directorioDestino: String){

    }

    fun resumen(directorioOrigen: String, directorioDestino: String){

    }

    fun resumenD(distrito: String ,directorioOrigen: String, directorioDestino: String){

    }
}