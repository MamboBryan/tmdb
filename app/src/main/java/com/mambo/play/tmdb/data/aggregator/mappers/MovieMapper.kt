package com.mambo.play.tmdb.data.aggregator.mappers

import com.mambo.play.nekos.data.sources.remote.dtos.MovieDTO
import com.mambo.play.tmdb.domain.models.MovieDomain

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 10:59 am
 */

fun MovieDTO.toDomain() = MovieDomain(title = title, image = posterPath, overview = overview)

// to DOMAIN

// to DTO from DOMAIN

// to Entity from Domain