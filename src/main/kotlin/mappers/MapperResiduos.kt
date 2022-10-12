package mappers

import dto.ResiduosDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Residuos
import nl.adaptivity.xmlutil.serialization.XML
import repositories.SerializableResiduosDTO
import java.io.File

class MapperResiduos {
    fun toResiduosDTO(residuos: Residuos): ResiduosDTO {
        return ResiduosDTO(
            anio = residuos.anio,
            mes = residuos.mes,
            lote = residuos.lote,
            residuos = residuos.residuos,
            distrito = residuos.distrito,
            nom_ditrito = residuos.nom_ditrito,
            toneladas = residuos.toneladas
        )
    }

    fun toResiduos(residuos: ResiduosDTO): Residuos {
        return Residuos(
            anio = residuos.anio,
            mes = residuos.mes,
            lote = residuos.lote,
            residuos = residuos.residuos,
            distrito = residuos.distrito,
            nom_ditrito = residuos.nom_ditrito,
            toneladas = residuos.toneladas
        )
    }

    fun leerCSV(ruta: String): List<ResiduosDTO> {
        val fichero = File(ruta)
        return fichero.readLines()
            .drop(1)
            .map { residuos -> residuos.split(";") }
            .map {
                it.map { it.trim() }
                ResiduosDTO(
                    anio = it[0].toIntOrNull(),
                    mes = it[1],
                    lote = it[2].toIntOrNull(),
                    residuos = it[3],
                    distrito = it[4].toIntOrNull(),
                    nom_ditrito = it[5],
                    toneladas = it[6].replace(",", ".").toFloat()
                )

            }
        fun exportarJSON(ruta: String, residuos: SerializableResiduosDTO) {
            val json= Json { prettyPrint=true }
            val fichero=File(ruta+File.separator+"fichero.json")
            fichero.writeText(json.encodeToString(residuos))
        }

        fun exportarXML(ruta: String, residuos:SerializableResiduosDTO){
            val xml= XML{indentString=" "}
            val fichero=File(ruta+File.separator+"fichero.xml")
            fichero.writeText(xml.encodeToString(residuos))
        }

    }
}