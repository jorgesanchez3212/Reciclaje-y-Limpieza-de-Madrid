package mappers

import dto.ResiduosDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Residuos
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class MapperResiduos {
    fun dtoTo(residuoDto: ResiduosDTO): Residuos {
        return Residuos(
            anio = residuoDto.anio,
            mes = residuoDto.mes,
            lote = residuoDto.lote,
            residuos = residuoDto.residuos,
            distrito = residuoDto.distrito,
            nom_ditrito = residuoDto.nom_ditrito,
            toneladas = residuoDto.toneladas

        )
    }

    fun toDto(residuo: Residuos): ResiduosDTO {
        return ResiduosDTO(
            anio = residuo.anio,
            mes = residuo.mes,
            lote = residuo.lote,
            residuos = residuo.residuos,
            distrito = residuo.distrito,
            nom_ditrito = residuo.nom_ditrito,
            toneladas = residuo.toneladas
        )
    }

    /*fun csvToResiduo(directorio: String): List<Residuos> {

        if(Files.lines(Path.of(directorio)).findFirst()==)

        return Files.lines(Path.of(directorio))
            .skip(1)
            .map { mapToResiduo(it) }.toList()

    }*/

    private fun mapToResiduo(line: String): Residuos {
        val it = line.split(";")
        return Residuos(
            anio = it[0],
            mes = it[1],
            lote = it[2],
            residuos = it[3],
            distrito = it[4],
            nom_ditrito = it[5],
            toneladas = it[6]
        )
    }
    fun toJson(directorio: String, listaResiduosDto: List<ResiduosDTO>) {
        val json = Json { prettyPrint=true }
        val fichero=File(directorio+File.separator+"fichero.json")
        fichero.writeText(json.encodeToString(listaResiduosDto))
    }
    fun jsonTo(directorio: String):List<ResiduosDTO> {
        val json=Json{prettyPrint=true}
        val fichero=File(directorio)
        return json.decodeFromString<List<ResiduosDTO>>(fichero.readText())
    }
    fun toXML (directorio: String, listaResiduosDto: List<ResiduosDTO>) {
        val xml = XML {indentString = "  "}
        val fichero = File(directorio+File.separator+"intercambio.xml")
        fichero.writeText(xml.encodeToString(listaResiduosDto))
    }
    fun xmlTO(directorio: String):List<ResiduosDTO>{
        val xml=XML{indentString="  "}
        val fichero=File(directorio)
        return xml.decodeFromString<List<ResiduosDTO>>(fichero.readText())
    }
}