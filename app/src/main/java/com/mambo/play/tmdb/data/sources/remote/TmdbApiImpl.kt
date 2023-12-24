package com.mambo.play.tmdb.data.sources.remote

import com.mambo.play.nekos.data.sources.remote.dtos.MovieDTO
import com.mambo.play.nekos.data.sources.remote.dtos.PagedResponseDTO
import com.mambo.play.tmdb.data.sources.remote.helpers.Endpoint
import com.mambo.play.tmdb.data.sources.remote.helpers.NetworkResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import java.io.IOException

class TmdbApiImpl(private val client: HttpClient) : TmdbApi {

    private fun HttpRequestBuilder.addApiKey() {
        headers {
            header(
                "Authorization",
                "Bearer get your own key"
            )
        }
    }

    data class Query(val name: String, val value: String)

    private fun HttpRequestBuilder.addUrl(endpoint: String, vararg queries: Query) {
        url(buildString {
            append(endpoint)
            append("?")
            queries.forEach { append("${it.name}=${it.value}&") }
        })
    }

    private suspend fun <T> safeApiCall(
        message: String = "Error",
        block: suspend () -> T
    ): NetworkResult<T> {
        return try {
            val data = block.invoke()
            NetworkResult.Success(data = data)
        } catch (e: IOException) {
            NetworkResult.Failure(message = e.localizedMessage ?: message)
        }
    }

    override suspend fun fetchMoviesList(page: Int): NetworkResult<PagedResponseDTO<MovieDTO>> =
        safeApiCall(message = "Failed to `fetch` movies list ") {
            val response = client.get {
                addApiKey()
                addUrl(
                    endpoint = Endpoint.Movies.url,
                    *arrayOf(
                        Query("include_adult", "false"),
                        Query("page", "$page")
                    )
                )
            }
            println(response.bodyAsText())
            response.body()
        }

}