package com.mambo.play.tmdb.data.sources.remote.helpers

sealed class Endpoint(private val route: String) {

    val url: String
        get() = buildString {
            append("https://api.themoviedb.org/3")
            append(route)
        }

    data object Movies : Endpoint("/discover/movie")

    data object Series : Endpoint("/discover/series") {
        data class Serie(val id: String) : Endpoint("/discover/series?id=$id")
    }

}