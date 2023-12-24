package com.mambo.play.tmdb.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.mambo.play.tmdb.ui.screens.landing.LandingScreen

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:27 am
 */

@Composable
fun MainNavigation() {
    Navigator(LandingScreen)
}