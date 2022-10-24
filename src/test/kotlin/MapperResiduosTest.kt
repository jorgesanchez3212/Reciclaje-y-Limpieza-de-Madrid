import mappers.MapperContenedor
import mappers.MapperResiduos
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertTrue

class MapperResiduosTest {
    var residuos = MapperResiduos()
    var residuoCSV="data"
    var rutaDestino ="metadata"
    var archivoCopiado = File("metadata/modelo_residuos_2021.csv")
    val residuosArchivo = "data"+ File.separator + "modelo_residuos_2021.csv"
    var archivoCopiadoJSON = File("metadata"+File.separator + "modelo_residuos_2021.json")
    var archivoCopiadoXML = File("metadata"+File.separator + "modelo_residuos_2021.xml")



    @Test
    fun copyCSVContenedorTests() {
        residuos.copiarCSV(residuoCSV,rutaDestino)
        assertTrue(archivoCopiado.exists())
        archivoCopiado.delete()
    }

    @Test
    fun leerCSVContenedorTests() {
        var a = residuos.leerCSV(residuosArchivo)
        residuos.exportarCSV(rutaDestino,a)
        assertTrue(archivoCopiado.exists())
        archivoCopiado.delete()

    }

    @Test
    fun exportarJSONContenedorTests() {
        var a = residuos.leerCSV(residuosArchivo)
        residuos.exportarJSON(rutaDestino,a)
        assertTrue(archivoCopiadoJSON.exists())
        archivoCopiadoJSON.delete()
    }

    @Test
    fun exportarXMLContenedorTests() {
        var a = residuos.leerCSV(residuosArchivo)
        residuos.exportarXML(rutaDestino,a)
        assertTrue(archivoCopiadoXML.exists())
        archivoCopiadoXML.delete()
    }
}