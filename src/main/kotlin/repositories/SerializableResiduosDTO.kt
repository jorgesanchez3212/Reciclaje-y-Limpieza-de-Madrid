package repositories

import dto.ResiduosDTO
import kotlinx.serialization.Serializable

@Serializable
data class SerializableResiduosDTO (
    val residuos: List<ResiduosDTO>
        )