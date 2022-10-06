@file:Suppress("UNCHECKED_CAST", "USELESS_CAST")
package models
import org.jetbrains.kotlinx.dataframe.annotations.*
import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup

val ColumnsContainer<models.Contenedores>.barrio: DataColumn<String> @JvmName("Contenedores_barrio") get() = this["barrio"] as DataColumn<String>
val DataRow<models.Contenedores>.barrio: String @JvmName("Contenedores_barrio") get() = this["barrio"] as String
val ColumnsContainer<models.Contenedores>.cantidad: DataColumn<Int> @JvmName("Contenedores_cantidad") get() = this["cantidad"] as DataColumn<Int>
val DataRow<models.Contenedores>.cantidad: Int @JvmName("Contenedores_cantidad") get() = this["cantidad"] as Int
val ColumnsContainer<models.Contenedores>.codInterno: DataColumn<Int> @JvmName("Contenedores_codInterno") get() = this["codInterno"] as DataColumn<Int>
val DataRow<models.Contenedores>.codInterno: Int @JvmName("Contenedores_codInterno") get() = this["codInterno"] as Int
val ColumnsContainer<models.Contenedores>.cordenadax: DataColumn<String> @JvmName("Contenedores_cordenadax") get() = this["cordenadax"] as DataColumn<String>
val DataRow<models.Contenedores>.cordenadax: String @JvmName("Contenedores_cordenadax") get() = this["cordenadax"] as String
val ColumnsContainer<models.Contenedores>.cordenaday: DataColumn<String> @JvmName("Contenedores_cordenaday") get() = this["cordenaday"] as DataColumn<String>
val DataRow<models.Contenedores>.cordenaday: String @JvmName("Contenedores_cordenaday") get() = this["cordenaday"] as String
val ColumnsContainer<models.Contenedores>.descripcionModelo: DataColumn<String> @JvmName("Contenedores_descripcionModelo") get() = this["descripcionModelo"] as DataColumn<String>
val DataRow<models.Contenedores>.descripcionModelo: String @JvmName("Contenedores_descripcionModelo") get() = this["descripcionModelo"] as String
val ColumnsContainer<models.Contenedores>.direccion: DataColumn<String> @JvmName("Contenedores_direccion") get() = this["direccion"] as DataColumn<String>
val DataRow<models.Contenedores>.direccion: String @JvmName("Contenedores_direccion") get() = this["direccion"] as String
val ColumnsContainer<models.Contenedores>.distrito: DataColumn<String> @JvmName("Contenedores_distrito") get() = this["distrito"] as DataColumn<String>
val DataRow<models.Contenedores>.distrito: String @JvmName("Contenedores_distrito") get() = this["distrito"] as String
val ColumnsContainer<models.Contenedores>.latitud: DataColumn<String> @JvmName("Contenedores_latitud") get() = this["latitud"] as DataColumn<String>
val DataRow<models.Contenedores>.latitud: String @JvmName("Contenedores_latitud") get() = this["latitud"] as String
val ColumnsContainer<models.Contenedores>.longitud: DataColumn<String> @JvmName("Contenedores_longitud") get() = this["longitud"] as DataColumn<String>
val DataRow<models.Contenedores>.longitud: String @JvmName("Contenedores_longitud") get() = this["longitud"] as String
val ColumnsContainer<models.Contenedores>.lote: DataColumn<Int> @JvmName("Contenedores_lote") get() = this["lote"] as DataColumn<Int>
val DataRow<models.Contenedores>.lote: Int @JvmName("Contenedores_lote") get() = this["lote"] as Int
val ColumnsContainer<models.Contenedores>.modelo: DataColumn<String> @JvmName("Contenedores_modelo") get() = this["modelo"] as DataColumn<String>
val DataRow<models.Contenedores>.modelo: String @JvmName("Contenedores_modelo") get() = this["modelo"] as String
val ColumnsContainer<models.Contenedores>.nombre: DataColumn<String> @JvmName("Contenedores_nombre") get() = this["nombre"] as DataColumn<String>
val DataRow<models.Contenedores>.nombre: String @JvmName("Contenedores_nombre") get() = this["nombre"] as String
val ColumnsContainer<models.Contenedores>.numero: DataColumn<Int?> @JvmName("Contenedores_numero") get() = this["numero"] as DataColumn<Int?>
val DataRow<models.Contenedores>.numero: Int? @JvmName("Contenedores_numero") get() = this["numero"] as Int?
val ColumnsContainer<models.Contenedores>.tipoContenedor: DataColumn<String> @JvmName("Contenedores_tipoContenedor") get() = this["tipoContenedor"] as DataColumn<String>
val DataRow<models.Contenedores>.tipoContenedor: String @JvmName("Contenedores_tipoContenedor") get() = this["tipoContenedor"] as String
val ColumnsContainer<models.Contenedores>.tipoVia: DataColumn<String> @JvmName("Contenedores_tipoVia") get() = this["tipoVia"] as DataColumn<String>
val DataRow<models.Contenedores>.tipoVia: String @JvmName("Contenedores_tipoVia") get() = this["tipoVia"] as String
