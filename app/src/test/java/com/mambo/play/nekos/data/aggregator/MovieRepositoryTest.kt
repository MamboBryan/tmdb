package com.mambo.play.nekos.data.aggregator

import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain
import com.mambo.play.tmdb.domain.respository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:08 am
 */

private class FakeMovieRepository : MoviesRepository {

    private val movies: MutableStateFlow<List<MovieDomain>?> = MutableStateFlow(listOf())

    fun updateMovies(updates: List<MovieDomain>? = null) {
        movies.value = updates
    }

    override suspend fun getMovies(): DataResult<List<MovieDomain>> {
        return when (val result = movies.value) {
            null -> DataResult.Failure("it is null")
            else -> {
                if (result.isEmpty())
                    DataResult.Empty
                else
                    DataResult.Success(data = result)
            }
        }
    }

    fun clear() {
        movies.value = null
    }

}

class MovieRepositoryTest {

    private lateinit var repository: FakeMovieRepository

    @Before
    fun setup() {
        repository = FakeMovieRepository()
    }

    @After
    fun tearDown() {
        repository.clear()
    }

    @Test
    fun `given MoviesRepository, when Failure occurs, returns DataResult Failure with message`() =
        runTest {
            repository.updateMovies(null)
            val movies = repository.getMovies()
            assert(movies is DataResult.Failure)
            assert((movies as DataResult.Failure).message.isNotBlank())
        }

    @Test
    fun `given MoviesRepository, when Success occurs with empty list, returns DataResult Empty`() =
        runTest {
            repository.updateMovies(listOf())
            val movies = repository.getMovies()
            assert(movies is DataResult.Empty)
        }

    @Test
    fun `given MoviesRepository, when Success occurs with data in list, returns DataResult Success`() =
        runTest {
            repository.updateMovies(listOf(MovieDomain("Tamzi's Calling", "")))
            val movies = repository.getMovies()
            assert(movies is DataResult.Success)
            val data = (movies as DataResult.Success).data
            assert(data.first().title == "Tamzi's Calling")
        }

}