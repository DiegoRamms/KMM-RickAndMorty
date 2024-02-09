package presentation

import data.model.Character

data class RickMortyStateUI (
    val characters: List<Character> = listOf(),
    val error: String? = null,
    val textTyped: String = ""
)