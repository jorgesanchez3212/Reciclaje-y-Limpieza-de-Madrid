import controllers.ResiduosController
//Ejemplo de prueba "resumen" "Barajas" "C:\GRADO SUPERIOR\SEGUNDO\Ingles\parser" "C:\GRADO SUPERIOR\SEGUNDO\Ingles\parser_destino"
fun main(args: Array<String>) {

    if (args[0].lowercase() == "parser") {
        val rutaOrigen = args[1]
        val rutaFinal = args[2]

        ResiduosController().parser(rutaOrigen,rutaFinal)

    } else if (args[0].lowercase() == "resumen" && args.size == 3) {
        val rutaOrigen = args[1]
        val rutaFinal = args[2]

        ResiduosController().resumen(rutaOrigen,rutaFinal)

    } else if (args[0].lowercase() == "resumen" && args.size==4) {
        val rutaOrigen = args[2]
        val rutaFinal = args[3]
        val distrito = args[1]
        ResiduosController().resumenDistrito(distrito,rutaOrigen,rutaFinal)
    }else{
        throw Exception("Argumentos Incorrectos")
    }
}



