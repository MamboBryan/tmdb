package com.mambo.play.tmdb.ui.screens.movies

import cafe.adriel.voyager.core.model.coroutineScope
import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain
import com.mambo.play.tmdb.domain.respository.MoviesRepository
import com.mambo.play.tmdb.ui.screens.helpers.StatefulScreenModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 12:17 pm
 */

data class MoviesScreenUiState(val data: DataResult<List<MovieDomain>> = DataResult.Loading)

class MoviesScreenModel @Inject constructor(
    private val repository: MoviesRepository
) : StatefulScreenModel<MoviesScreenUiState>(MoviesScreenUiState()) {

    init {
        getMovies()
    }

    fun getMovies() {
        update { copy(data = DataResult.Loading) }
        coroutineScope.launch {
            val result = repository.getMovies()
            update { copy(data = result) }
        }
    }

}