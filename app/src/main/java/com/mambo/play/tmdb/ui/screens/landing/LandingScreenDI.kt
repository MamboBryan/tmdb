package com.mambo.play.tmdb.ui.screens.landing

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
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
 * @time    : 11:58 am
 */

@Module
@InstallIn(ActivityComponent::class)
abstract class LandingScreenDI {

    @Binds
    @IntoMap
    @ScreenModelKey(LandingScreenModel::class)
    abstract fun bind(screenModel: LandingScreenModel): ScreenModel

}