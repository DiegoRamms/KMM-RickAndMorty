package presentation

import data.model.CharactersResponse
import di.RickMortyRepository
import domain.BaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: RickMortyRepository) {


    private val _charactersState = MutableStateFlow<CharactersResponse?>(null)
    val charactersResponse get() = _charactersState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState get() = _errorState.asStateFlow()


    init {
        getCharacters()
    }

    private fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {

            when(val response = repository.getCharacters()){
                is BaseResult.Error -> { _errorState.update { response.exception.message.toString()  }}
                is BaseResult.Success -> { _charactersState.update { response.data }}
            }
        }

    }

}