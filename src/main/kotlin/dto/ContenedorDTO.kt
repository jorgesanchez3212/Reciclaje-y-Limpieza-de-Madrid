package dto

import kotlinx.serialization.Serializable

/**
 * Contenedor d t o
 * @author Jorge y Alfredo
 * @since 19/10/2022
 * @property codInterno
 * @property tipoContenedor
 * @property modelo
 * @property descripcionModelo
 * @property cantidad
 * @property lote
 * @property distrito
 * @property barrio
 * @property tipoVia
 * @property nombre
 * @property numero
 * @property cordenadax
 * @property cordenaday
 * @property longitud
 * @property latitud
 * @property direccion
 */
@Serializable
data class ContenedorDTO(val codInterno: Int,
                    val tipoContenedor: String,
                    val modelo: String,
                    val descripcionModelo: String,
                    val cantidad: Int,
                    val lote: Int,
                    val distrito: String,
                    val barrio: String = "NO EXISTE",
                    val tipoVia: String,
                    val nombre: String,
                    val numero: Int?,
                    val cordenadax: String,
                    val cordenaday: String,
                    val longitud: String,
                    val latitud: String,
                    val direccion: String
) {

}