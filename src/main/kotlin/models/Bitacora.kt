package models

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement
import java.io.File
import java.time.LocalDateTime
import java.util.*
@Serializable
class Bitacora(
    @XmlElement (true)
    val id: String = UUID.randomUUID().toString(),
    @XmlElement(true)
    val instante: String = LocalDateTime.now().toString(),
    @XmlElement(true)
    val opcionElegida: String,
    @XmlElement(true)
    val exito: Boolean,
    @XmlElement(true)
    val tiempoEjecucion: Long,
    @XmlElement(true)
    val path: String
) {

    init {
        bitacora()
    }

    private fun bitacora(){
        val xml = XML { indentString = " " }
        val fichero = File(path + File.separator + "bitacora.xml")
        fichero.writeText(xml.encodeToString(this))
    }
}