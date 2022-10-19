package utils

import java.io.File

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

    fun directorioHtml(path: String): String{
        var ruta = path+ File.separator+"style.css"
        return """" <!DOCTYPE html>
            <html lang="en">
            <head>
                <title>Resumen</title>
                <link rel="stylesheet" href="$ruta">
            </head>
            <body >
            <div class="id">
            <h1>Resumen de recogidas de basura y reciclaje de Madrid</h1>
            </div>
            <div class="bodyy">
            <ul>
            <li><h3> Fecha de generación: "$tiempoGeneracion"</h3></li>
            <li><h3>Autores: Jorge Sánchez Berrocoso y Alfredo Maldonado</h3></li>
            <li><h5>Número de contenedores de cada tipo que hay en cada distrito: $numContenedoresDistrito</h5></li>
            <li><h5>Media de contenedores de cada tipo que hay: $mediaContenedoresDistrito</h5></li>
            <li><h5>Gráfico con el total de contenedores por distrito:</h5></li>
            <img src="graficoTotalContenedoresPorDistrito.png/totalContenedores.png">
            <li><h5>Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
            distrito: $mediaToneladasAnualesTipoBasuraDistrito</h5></li>
            <li><h5>Gráfico de media de toneladas mensuales de recogida de basura por distrito:</h5></li>
            <img src="graficoToneladasMensualesPorDistrito.png/graficoToneladasMensualesPorDistrito.png">
            <li><h5>Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
            de basura agrupadas por distrito: $maxminavgDesviacionToneladasDistrito</h5></li>
            <li><h5>Suma de todo lo recogido en un año por distrito: $sumRecogidoAnualDistrito</h5></li>
            <li><h5>Por cada distrito obtener para cada tipo de residuo la cantidad recogida: $cantidadRecogidaTipoPorDistrito</h5></li>
            <li><h5>Tiempo de generacion del mismo en milisegundos: $tiempoGeneracion</h5></li>
        </ul>
        </div>
           
            </body>
            </html>
            
            """.trimIndent()
    }

    fun distrito(path:String): String{
        var ruta = path+ File.separator+"style.css"
        return """ <!DOCTYPE html>
            <html lang="en">
            <head>
                <title>Resumen</title>
                <link rel="stylesheet" href="$ruta">
            </head>
            <body>
            <div class="id">
            <h1>Resumen de recogidas de basura y reciclaje de $distrito</h1>
            </div>
            <div class="bodyy">
                <ul>
            <il><h3> Fecha de generación: $fechaHoras</h3></il>
            <il><h3>Autores: Jorge Sánchez Berrocoso y Alfredo Maldonado</h3></il>
            <il><h5>Número de contenedores de cada tipo que hay en este distrito: $numContenedoresDistrito </h5></il>
            <il><h5>Total de toneladas recogidas en ese distrito por residuo: $totalResiduos</h5></il>
            <il><h5>Gráfico con el total de toneladas por residuo en ese distrito:</h5></il>
           <img src="graficaTotalToneladasPorResiduoDistrito.png/graficaTotalToneladasPorResiduoDistrito.png">
           <il><h5>Máximo, mínimo , media y desviación por mes por residuo en dicho distrito:$maxminavgDesviacionToneladasDistrito</h5></il>
           <il><h5>Gráfica del máximo, mínimo y media por meses en dicho distrito:</h5></il>
           <img src="graficaMaxMinMediaMesesDistrito.png/graficaMaxMinMediaMesesDistrito.png">
           <il><h5>Tiempo de generación del mismo en milisegundos:$tiempoGeneracion</h5></il>
        </ul>
           </div>
           
            </body>
            </html>
            
            """.trimIndent()
    }




}