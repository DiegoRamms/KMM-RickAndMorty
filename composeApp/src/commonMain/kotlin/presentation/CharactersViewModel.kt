package presentation

import data.model.Character
import domain.BaseResult
import domain.RickMortyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: RickMortyRepository) {

    private val _uiState  = MutableStateFlow(RickMortyStateUI())
    val uiState get() = _uiState.asStateFlow()
    private val allItems: MutableList<Character> = mutableListOf()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        CoroutineScope(Dispatchers.Main).launch {
            when (val response = repository.getCharacters()) {
                is BaseResult.Error -> {
                    _uiState.update { _uiState.value.copy(error = response.exception.message.toString()) }
                }

                is BaseResult.Success -> {
                    _uiState.update { _uiState.value.copy(characters = response.data.characters) }
                    allItems.addAll(response.data.characters)
                }
            }
        }
    }

    fun searchCharacter(text: String) {
        val auxText = text.trim().replace("\\s+".toRegex(), "")
        _uiState.update { _uiState.value.copy(textTyped = text) }
        if (auxText.isEmpty()) {
            _uiState.update { _uiState.value.copy(characters = allItems) }
            return
        }

        _uiState.update { _uiState.value.copy(characters = getCharactersFiltered(auxText)) }

    }

    private fun getCharactersFiltered(textFilter: String) =
        allItems.filter {
            it.name.trim().replace("\\s+".toRegex(), "")
                .contains(textFilter, ignoreCase = true) || it.species.trim()
                .replace("\\s+".toRegex(), "")
                .contains(textFilter, ignoreCase = true)
        }

}
