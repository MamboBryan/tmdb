package com.mambo.play.tmdb.ui.screens.landing

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.mambo.play.tmdb.ui.screens.helpers.StatefulScreenModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:31 am
 */


data class LandingScreenUiState(val destination: Destination? = null) {
    enum class Destination {
        Authentication,
        Home
    }
}

class LandingScreenModel @Inject constructor(

) : StatefulScreenModel<LandingScreenUiState>(LandingScreenUiState()) {

    init {
        checkNavigationDestination()
    }

    private fun checkNavigationDestination() {
        coroutineScope.launch {
            // load data from backend
            update { copy(destination = LandingScreenUiState.Destination.Home) }
        }
    }

}