package com.mambo.play.tmdb.data.aggregator.di

import com.mambo.play.tmdb.data.aggregator.repos.MoviesRepositoryImpl
import com.mambo.play.tmdb.data.sources.remote.TmdbApi
import com.mambo.play.tmdb.domain.respository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:01 am
 */

@Module
@InstallIn(SingletonComponent::class)
class AggregatorModule {

    @Provides
    fun provideMovieRepository(api: TmdbApi): MoviesRepository = MoviesRepositoryImpl(api = api)

}