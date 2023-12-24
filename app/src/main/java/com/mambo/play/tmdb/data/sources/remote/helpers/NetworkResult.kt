package com.mambo.play.tmdb.data.sources.remote.helpers

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 10:44 am
 */
sealed interface NetworkResult<out T> {

    data class Failure(val message: String) : NetworkResult<Nothing>

    data class Success<T>(val data: T) : NetworkResult<T>

}