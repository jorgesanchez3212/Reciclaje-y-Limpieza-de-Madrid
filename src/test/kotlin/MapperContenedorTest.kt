import dto.ContenedorDTO
import mappers.MapperContenedor
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class MapperContenedorTest {
    var contenedor = MapperContenedor()
    var contenedorCSV="data"
    var rutaDestino ="metadata"
    var archivoCopiado = File("metadata/contenedores_varios.csv")
    val contenedores_varios = "data"+File.separator + "contenedores_varios.csv"
    var archivoCopiadoJSON = File("metadata/contenedores_varios.json")
    var archivoCopiadoXML = File("metadata/contenedores_varios.xml")



    @Test
    fun copyCSVContenedorTests() {
        contenedor.copiarCSV(contenedorCSV,rutaDestino)
        assertTrue(archivoCopiado.exists())
        archivoCopiado.delete()
    }

    @Test
    fun leerCSVContenedorTests() {
        var a = contenedor.leerCSV(contenedores_varios)
        contenedor.exportarCSV(rutaDestino,a)
        assertTrue(archivoCopiado.exists())
        archivoCopiado.delete()

    }

    @Test
    fun exportarJSONContenedorTests() {
        var a = contenedor.leerCSV(contenedores_varios)
        contenedor.exportarJSON(rutaDestino,a)
        assertTrue(archivoCopiadoJSON.exists())
        archivoCopiadoJSON.delete()
    }

    @Test
    fun exportarXMLContenedorTests() {
        var a = contenedor.leerCSV(contenedores_varios)
        contenedor.exportarXML(rutaDestino,a)
        assertTrue(archivoCopiadoXML.exists())
        archivoCopiadoXML.delete()
    }
}