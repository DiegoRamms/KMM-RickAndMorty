package di

import data.model.CharactersResponse
import domain.BaseResult

interface RickMortyRepository {

    suspend fun getCharacters(): BaseResult<CharactersResponse>

}