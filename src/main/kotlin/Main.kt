import controllers.DataFrameController
import mappers.MapperContenedor
import mappers.MapperResiduos
import org.locationtech.jts.io.OutputStreamOutStream
import java.io.*

fun main(args: Array<String>){
    var a = MapperResiduos().leerCSV("data/modelo_residuos_2021.csv")
    var b = MapperContenedor().leerCSV("data/contenedores_varios.csv")

    val ab = DataFrameController(a,b)
    ab.resumen()
    ab.resumenDistrito("Barajas")
    
}



