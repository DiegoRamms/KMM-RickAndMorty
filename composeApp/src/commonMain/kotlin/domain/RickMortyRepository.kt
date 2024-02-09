package domain

import data.model.CharactersResponse

interface RickMortyRepository {

    suspend fun getCharacters(): BaseResult<CharactersResponse>

}