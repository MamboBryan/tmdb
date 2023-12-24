package com.mambo.play.tmdb.domain.models

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 10:56 am
 */
sealed interface DataResult<out T> {

    data object Loading: DataResult<Nothing>

    data object Empty : DataResult<Nothing>

    data class Failure(val message: String) : DataResult<Nothing>

    data class Success<T>(val data: T) : DataResult<T>

}