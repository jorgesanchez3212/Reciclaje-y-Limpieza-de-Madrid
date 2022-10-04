package dto

import kotlinx.serialization.Serializable

@Serializable
data class ContenedorDTO(val codInterno: String,
                    val tipoContenedor: String,
                    val modelo: String,
                    val descripcionModelo: String,
                    val cantidad: Int,
                    val lote: Int,
                    val distrito: String,
                    val barrio: String = "NO EXISTE",
                    val tipoVia: String,
                    val nombre: String,
                    val numero: Int,
                    val cordenadax: String,
                    val cordenaday: String,
                    val longitud: String,
                    val latitud: String,
                    val direccion: String
) {

    fun primeraLinea() : String{
        return "$codInterno;$tipoContenedor;$modelo;$descripcionModelo;$cantidad;$lote;$distrito;$barrio;$tipoVia;$nombre;"+
                "$numero;$cordenadax;$cordenaday;$longitud;$latitud;$direccion"
    }
}