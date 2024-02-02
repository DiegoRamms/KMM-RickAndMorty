package repository

import data.datasource.detworkdatasource.NetworkCharactersDataSource
import data.model.CharactersResponse
import di.RickMortyRepository
import domain.BaseResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RickMortyRepositoryImp(private val networkCharactersDataSource: NetworkCharactersDataSource): RickMortyRepository {

    override suspend fun getCharacters(): BaseResult<CharactersResponse> {
        return networkCharactersDataSource.getCharacters()
    }

}