package models

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement
import java.io.File
import java.time.LocalDateTime
import java.util.*
@Serializable
class Bitacora(
    @XmlElement (true)
    val id: String ,
    @XmlElement(true)
    val instante: String ,
    @XmlElement(true)
    val opcionElegida: String,
    @XmlElement(true)
    val exito: Boolean,
    @XmlElement(true)
    val tiempoEjecucion: Long,
    @XmlElement(true)
    val path: String
) {


     fun bitacora(){
        val xml = XML { indentString = " " }
        val fichero = File(path + File.separator + "bitacora.xml")
        if(fichero.exists()){
            fichero.appendText(xml.encodeToString(this))
        }else{
            fichero.createNewFile()
            fichero.writeText(xml.encodeToString(this))
        }

    }
}