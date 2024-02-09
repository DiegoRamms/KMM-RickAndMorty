package data.repository

import data.datasource.detworkdatasource.NetworkCharactersDataSource
import data.model.CharactersResponse
import domain.RickMortyRepository
import domain.BaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RickMortyRepositoryImp(private val networkCharactersDataSource: NetworkCharactersDataSource):
    RickMortyRepository {

    override suspend fun getCharacters(): BaseResult<CharactersResponse> {
        return withContext(Dispatchers.IO){
            networkCharactersDataSource.getCharacters()
        }
    }

}