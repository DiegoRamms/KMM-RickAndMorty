package data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.math.log


fun provideClient() = HttpClient(){

    install(ContentNegotiation){
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(Logging){
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
        url("https://rickandmortyapi.com/api/")
    }

}

