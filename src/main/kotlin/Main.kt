import controllers.DataFrameController

fun main(args: Array<String>){
    DataFrameController().resumen("data/modelo_residuos_2021.csv","data/contenedores_varios.csv")
    DataFrameController().resumenDistrito("Barajas","data/modelo_residuos_2021.csv","data/contenedores_varios.csv")
}



