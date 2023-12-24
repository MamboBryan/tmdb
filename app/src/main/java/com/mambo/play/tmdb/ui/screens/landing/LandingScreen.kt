package com.mambo.play.tmdb.ui.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mambo.play.tmdb.ui.screens.movies.MoviesScreen

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:30 am
 */
object LandingScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: LandingScreenModel = getScreenModel()
        val state by screenModel.state.collectAsState()
        LandingScreenContent(state = state) {
            when (it) {
                LandingScreenUiState.Destination.Authentication -> navigator.replace(MoviesScreen)
                LandingScreenUiState.Destination.Home -> navigator.replace(MoviesScreen)
            }
        }
    }
}

@Composable
fun LandingScreenContent(
    state: LandingScreenUiState,
    navigateToDestination: (LandingScreenUiState.Destination) -> Unit
) {

    LaunchedEffect(state.destination) {
        state.destination?.let {
            when (it) {
                LandingScreenUiState.Destination.Authentication -> navigateToDestination(
                    LandingScreenUiState.Destination.Authentication
                )

                LandingScreenUiState.Destination.Home -> navigateToDestination(
                    LandingScreenUiState.Destination.Home
                )
            }
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "TMDB")
        }
    }
}

