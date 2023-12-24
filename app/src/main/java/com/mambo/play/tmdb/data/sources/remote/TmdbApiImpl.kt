package com.mambo.play.tmdb.data.sources.remote

import com.mambo.play.nekos.data.sources.remote.dtos.MovieDTO
import com.mambo.play.nekos.data.sources.remote.dtos.PagedResponseDTO
import com.mambo.play.tmdb.data.sources.remote.helpers.Endpoint
import com.mambo.play.tmdb.data.sources.remote.helpers.NetworkResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.io.IOException

class TmdbApiImpl(private val client: HttpClient) : TmdbApi {

    private fun HttpRequestBuilder.addApiKey() {
        headersOf(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMWIzMmIyZDc3ZDlmMWE1OTViOGFiMDViNzk5YjgzNiIsInN1YiI6IjY1MjlhMGQ0ZjI4ODM4MDJhMzI3MDI0NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7a4jQN3t3jWgAoGBitzrgn6J3g8C3R0lEzC7kT09CKE"
        )
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
            response.body()
        }

}