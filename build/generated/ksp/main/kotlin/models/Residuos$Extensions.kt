@file:Suppress("UNCHECKED_CAST", "USELESS_CAST")
package models
import org.jetbrains.kotlinx.dataframe.annotations.*
import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.columns.ColumnGroup

val ColumnsContainer<models.Residuos>.anio: DataColumn<Int?> @JvmName("Residuos_anio") get() = this["anio"] as DataColumn<Int?>
val DataRow<models.Residuos>.anio: Int? @JvmName("Residuos_anio") get() = this["anio"] as Int?
val ColumnsContainer<models.Residuos>.distrito: DataColumn<Int?> @JvmName("Residuos_distrito") get() = this["distrito"] as DataColumn<Int?>
val DataRow<models.Residuos>.distrito: Int? @JvmName("Residuos_distrito") get() = this["distrito"] as Int?
val ColumnsContainer<models.Residuos>.lote: DataColumn<Int?> @JvmName("Residuos_lote") get() = this["lote"] as DataColumn<Int?>
val DataRow<models.Residuos>.lote: Int? @JvmName("Residuos_lote") get() = this["lote"] as Int?
val ColumnsContainer<models.Residuos>.mes: DataColumn<String> @JvmName("Residuos_mes") get() = this["mes"] as DataColumn<String>
val DataRow<models.Residuos>.mes: String @JvmName("Residuos_mes") get() = this["mes"] as String
val ColumnsContainer<models.Residuos>.nom_ditrito: DataColumn<String> @JvmName("Residuos_nom_ditrito") get() = this["nom_ditrito"] as DataColumn<String>
val DataRow<models.Residuos>.nom_ditrito: String @JvmName("Residuos_nom_ditrito") get() = this["nom_ditrito"] as String
val ColumnsContainer<models.Residuos>.residuos: DataColumn<String> @JvmName("Residuos_residuos") get() = this["residuos"] as DataColumn<String>
val DataRow<models.Residuos>.residuos: String @JvmName("Residuos_residuos") get() = this["residuos"] as String
val ColumnsContainer<models.Residuos>.toneladas: DataColumn<Float> @JvmName("Residuos_toneladas") get() = this["toneladas"] as DataColumn<Float>
val DataRow<models.Residuos>.toneladas: Float @JvmName("Residuos_toneladas") get() = this["toneladas"] as Float
