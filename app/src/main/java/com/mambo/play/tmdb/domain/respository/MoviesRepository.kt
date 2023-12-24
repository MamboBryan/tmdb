package com.mambo.play.tmdb.domain.respository

import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 10:52 am
 */
interface MoviesRepository {

    suspend fun getMovies(): DataResult<List<MovieDomain>>

}