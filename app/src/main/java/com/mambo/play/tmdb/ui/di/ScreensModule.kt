package com.mambo.play.tmdb.ui.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.mambo.play.tmdb.ui.screens.landing.LandingScreenModel
import com.mambo.play.tmdb.ui.screens.moviedetail.MovieDetailScreenModel
import com.mambo.play.tmdb.ui.screens.movies.MoviesScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 12:16 pm
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class ScreensModule {

    @Binds
    @IntoMap
    @ScreenModelKey(MoviesScreenModel::class)
    abstract fun bindMovies(screenModel: MoviesScreenModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(LandingScreenModel::class)
    abstract fun bindLanding(screenModel: LandingScreenModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(MovieDetailScreenModel::class)
    abstract fun bindMovieDetail(screenModel: MovieDetailScreenModel): ScreenModel

}
