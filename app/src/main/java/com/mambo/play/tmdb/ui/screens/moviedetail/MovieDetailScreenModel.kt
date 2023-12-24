package com.mambo.play.tmdb.ui.screens.moviedetail

import com.mambo.play.tmdb.domain.models.DataResult
import com.mambo.play.tmdb.domain.models.MovieDomain
import com.mambo.play.tmdb.ui.screens.helpers.StatefulScreenModel
import javax.inject.Inject

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 1:19 pm
 */

data class MovieDetailScreenUiState(
    val title: String = "",
    val dataResult: DataResult<MovieDomain> = DataResult.Loading
)

class MovieDetailScreenModel @Inject constructor(

) : StatefulScreenModel<MovieDetailScreenUiState>(MovieDetailScreenUiState()) {

    fun updateMovie(movieDomain: MovieDomain) {
        update {
            copy(
                title = movieDomain.title,
                dataResult = DataResult.Success(data = movieDomain)
            )
        }
    }

}