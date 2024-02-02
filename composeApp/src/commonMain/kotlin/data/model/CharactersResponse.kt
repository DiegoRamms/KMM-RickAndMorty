package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info,
    @SerialName("results")
    val characters: List<Character>
)