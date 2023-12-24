package com.mambo.play.nekos.data.sources.remote

import com.mambo.play.tmdb.data.sources.remote.TmdbApi
import com.mambo.play.tmdb.data.sources.remote.TmdbApiImpl
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private fun generateFakeClient(engine: HttpClientEngine) = HttpClient(engine) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}

private fun generateFakeEngine(statusCode: HttpStatusCode, response: String) = MockEngine {
    val headers = headers {
        append(HttpHeaders.ContentType, "application/json")
        append(HttpHeaders.Authorization, "Bearer api key")
    }
    respond(content = response, status = statusCode, headers = headers)
}

fun generateFakeTmdbApi(statusCode: HttpStatusCode, response: String): TmdbApi = TmdbApiImpl(
    client = generateFakeClient(engine = generateFakeEngine(statusCode, response))
)