import controllers.DataFrame
import utils.Utils
import java.util.*

fun main(args: Array<String>){
    DataFrame().resumen("data/modelo_residuos_2021.csv","data/contenedores_varios.csv")
    DataFrame().resumenDistrito("Barajas","data/modelo_residuos_2021.csv","data/contenedores_varios.csv")
}



