package utils

import java.io.File


class Utils {
    fun comprobarExtension(rutaOrigen: String): Boolean {
        return rutaOrigen.endsWith(".json") || rutaOrigen.endsWith(".csv") || rutaOrigen.endsWith(".xml")
    }

    fun comprobarRuta(rutaOrigen: String, rutaFinal: String): Boolean {
        return File(rutaOrigen).exists() && File(rutaFinal).exists()
    }

}