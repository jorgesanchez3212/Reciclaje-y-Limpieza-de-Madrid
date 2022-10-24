# Reciclaje y Limpieza de Madrid
<p align="center">
  <a href="#">
    <img src="https://www.madrid.es/UnidadesDescentralizadas/LimpiezaUrbanaYResiduos/Campa%C3%B1as/00.%20CAMPA%C3%91AS%20DE%20INFORMACI%C3%93N%20Y%20SENSIBILIZACI%C3%93N/01%20CAMPA%C3%91A%20%22Con%20Erre%20De%22/CuidarMadridEsSencilloEntreTodos_690.png" />
  </a>
</p>

## Descripción

Nos ha contratado el Ayuntamiento de Madrid para que hagamos un estudio del análisis de
limpieza y gestión de basuras que se hace en la ciudad. Para ello hemos creado esta aplicación.

Nuestra aplicación funciona de forma que le debemos pasar 3, 4 argumentos, en función de la opción elegida.

Si la opción es parser directorio_origen directorio_destino: debe tomar los ficheros csv
del directorio origen y los trasforma en JSON y XML en el directorio destino. En dicho
directorio destino deberán estar las tres versiones: CSV, JSON y XML.

Si la opción es resumen directorio_origen directorio_destino debe tomar la información
de los contenedores y de la recogida, independientemente de la extensión que tenga  y deberá procesarla
generando en directorio_destino un resumen.html.

Si la opción es resumen distrito directorio_origen directorio_destino debe tomar la
información de los contenedores y de la recogida, independientemente de la extensión que
tenga y deberá procesarla generando en directorio_destino un resumen.html.



## Utilidades
- Ficheros CSV, JSON, XML input.
- Fichero CSV, JSON, XML Y HTML output.
- Expresiones regulares.



## Librerías usadas 📚
- Serialization.
- Data Frame
- LetsPlot.
- Kotlin-Logging-jvm
- Data Frame.
- Junit-Jupiter.


## Autores
- Jorge: <https://github.com/jorgesanchez3212>
- Alfredo: <https://github.com/reyalfre>


