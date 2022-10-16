package utils

class Html(
    val numContenedoresDistrito: String,
           val mediaContenedoresDistrito:String,
           val mediaToneladasAnualesTipoBasuraDistrito: String,
           val maxminavgDesviacionToneladasDistrito: String,
           val sumRecogidoAnualDistrito: String,
           val cantidadRecogidaTipoPorDistrito: String,
           val tiempoGeneracion: String,
           val distrito: String,
            val fechaHoras: String,
            val totalResiduos: String

           ) {

    fun directorioHtml(): String{
        return """" <!DOCTYPE html>
            <html lang="en">
            <head>
                <title>Resumen</title>
            </head>
            <body>
            <h1>Resumen de recogidas de basura y reciclaje de Madrid</h1>
            <h3> Fecha de generación: "$tiempoGeneracion"</h3>
            <h3>Autores: Jorge Sánchez Berrocoso y Alfredo Maldonado</h3>
            <h5>Número de contenedores de cada tipo que hay en cada distrito: $numContenedoresDistrito</h5>
            <h5>Media de contenedores de cada tipo que hay: $mediaContenedoresDistrito</h5>
            <h5>Gráfico con el total de contenedores por distrito:</h5>
            <img src="">
            <h5>Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
            distrito: $mediaToneladasAnualesTipoBasuraDistrito</h5>
            <h5>Gráfico de media de toneladas mensuales de recogida de basura por distrito:</h5>
            <img src="">
            <h5>Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
            de basura agrupadas por distrito: $maxminavgDesviacionToneladasDistrito</h5>
            <h5>Suma de todo lo recogido en un año por distrito: $sumRecogidoAnualDistrito</h5>
            <h5>Por cada distrito obtener para cada tipo de residuo la cantidad recogida: $cantidadRecogidaTipoPorDistrito</h5>
            <h5>Tiempo de generacion del mismo en milisegundos: $tiempoGeneracion</h5>
           
            </body>
            </html>
            
            """.trimIndent()
    }

    fun distrito(): String{
        return """" <!DOCTYPE html>
            <html lang="en">
            <head>
                <title>Resumen</title>
            </head>
            <body>
            <h1>Resumen de recogidas de basura y reciclaje de $distrito</h1>
            <h3> Fecha de generación: $fechaHoras</h3>
            <h3>Autores: Jorge Sánchez Berrocoso y Alfredo Maldonado</h3>
           <h5>Número de contenedores de cada tipo que hay en este distrito: $numContenedoresDistrito </h5>
            <h5>Total de toneladas recogidas en ese distrito por residuo: $totalResiduos</h5>
           <h5>Gráfico con el total de toneladas por residuo en ese distrito:</h5>
           <img src="">
           <h5>Máximo, mínimo , media y desviación por mes por residuo en dicho distrito:$maxminavgDesviacionToneladasDistrito</h5>
           <h5>Gráfica del máximo, mínimo y media por meses en dicho distrito:</h5>
           <img src="">
           <h5>Tiempo de generación del mismo en milisegundos:$tiempoGeneracion</h5>
           
            </body>
            </html>
            
            """.trimIndent()
    }


}