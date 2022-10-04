package dto

import kotlinx.serialization.Serializable

@Serializable
data class ResiduosDTO(  val anio: String,
                         val mes: String,
                         val lote: String,
                         val residuos: String,
                         val distrito: String,
                         val nom_ditrito: String,
                         val toneladas: String) {

    fun primeraLinea(): String{
        return "$anio;$mes;$lote;$residuos;$distrito;$nom_ditrito;$toneladas"
    }
}