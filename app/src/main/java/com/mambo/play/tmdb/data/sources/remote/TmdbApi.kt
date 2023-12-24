package com.mambo.play.tmdb.data.sources.remote

import com.mambo.play.nekos.data.sources.remote.dtos.MovieDTO
import com.mambo.play.nekos.data.sources.remote.dtos.PagedResponseDTO
import com.mambo.play.tmdb.data.sources.remote.helpers.NetworkResult

interface TmdbApi {
    
    /**
     * Fetches movies from the TMDB api
     * @param page  
     * returns [PagedResponseDTO]
     */
    suspend fun fetchMoviesList(page: Int = 1): NetworkResult<PagedResponseDTO<MovieDTO>>
    
}