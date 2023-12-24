package com.mambo.play.nekos.data.sources.remote

import com.mambo.play.nekos.data.sources.remote.dtos.MovieDTO
import com.mambo.play.nekos.data.sources.remote.dtos.PagedResponseDTO
import com.mambo.play.tmdb.data.sources.remote.helpers.NetworkResult
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

class TmdbApiTest {

    val moviesResponse = """
    {
    "page": 1,
    "results": [
    {
    "adult": false,
    "backdrop_path": "/5a4JdoFwll5DRtKMe7JLuGQ9yJm.jpg",
    "genre_ids": [
    18,
    878,
    28
    ],
    "id": 695721,
    "original_language": "en",
    "original_title": "The Hunger Games: The Ballad of Songbirds & Snakes",
    "overview": "64 years before he becomes the tyrannical president of Panem, Coriolanus Snow sees a chance for a change in fortunes when he mentors Lucy Gray Baird, the female tribute from District 12.",
    "popularity": 2222.913,
    "poster_path": "/mBaXZ95R2OxueZhvQbcEWy2DqyO.jpg",
    "release_date": "2023-11-15",
    "title": "The Hunger Games: The Ballad of Songbirds & Snakes",
    "video": false,
    "vote_average": 7.3,
    "vote_count": 924
    }
    ],
    "total_pages": 41618,
    "total_results": 832355
    }
    """.trimIndent()

    val json = Json {
        ignoreUnknownKeys = true
    }

    @Test
    fun `given TmbdApi, when fetching Movies, should return successful PagedResponse`() = runTest {
        val api = generateFakeTmdbApi(statusCode = HttpStatusCode.OK, response = moviesResponse)
        val response = api.fetchMoviesList(page = 1)
        val data: PagedResponseDTO<MovieDTO> = json.decodeFromString(moviesResponse)

        assert(response is NetworkResult.Success)
        val responseData = (response as NetworkResult.Success).data
        assert(data == responseData)
        assert(responseData.results.first().title == "The Hunger Games: The Ballad of Songbirds & Snakes")
    }

    @Test
    fun `given TmbdApi, when fetching Movies, should not return empty results`() = runTest {
        val api = generateFakeTmdbApi(statusCode = HttpStatusCode.OK, response = moviesResponse)
        val response = api.fetchMoviesList(page = 1)
        assert(response is NetworkResult.Success)
        val data = (response as NetworkResult.Success).data
        assert(data.results.isNotEmpty())
    }

}