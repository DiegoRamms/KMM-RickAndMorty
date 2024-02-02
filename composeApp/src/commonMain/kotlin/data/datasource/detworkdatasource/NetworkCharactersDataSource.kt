package data.datasource.detworkdatasource

import data.model.CharactersResponse
import domain.BaseResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class NetworkCharactersDataSource(private val client: HttpClient) {
    suspend fun getCharacters(): BaseResult<CharactersResponse> {
        return try {
            val response: CharactersResponse = client.get("/api/character/?page=1").body()

            BaseResult.Success(response)
        } catch (e : Exception) {
            println(e)
            BaseResult.Error(e)
        }
    }
}