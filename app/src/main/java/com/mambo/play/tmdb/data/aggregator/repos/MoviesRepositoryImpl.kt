package com.mambo.play.tmdb.data.aggregator.repos

import com.mambo.play.tmdb.data.aggregator.mappers.toDomain
import com.mambo.play.tmdb.data.sources.remote.TmdbApi
import com.mambo.play.tmdb.data.sources.remote.helpers.NetworkResult
import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain
import com.mambo.play.tmdb.domain.respository.MoviesRepository

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 10:54 am
 */
class MoviesRepositoryImpl(private val api: TmdbApi) : MoviesRepository {

    override suspend fun getMovies(): DataResult<List<MovieDomain>> {
        return when (val result = api.fetchMoviesList(page = 1)) {
            is NetworkResult.Failure -> DataResult.Failure(message = result.message)
            is NetworkResult.Success -> {
                val data = result.data
                if (data.results.isEmpty())
                    DataResult.Empty
                else
                    DataResult.Success(data = data.results.map { dto -> dto.toDomain() })
            }
        }
    }

}