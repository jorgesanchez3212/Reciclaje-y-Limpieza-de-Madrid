package repositories

import dto.ContenedorDTO
import kotlinx.serialization.Serializable

@Serializable
data class SerializableContenedorDTO(
    val contenedores: List<ContenedorDTO>
    )
