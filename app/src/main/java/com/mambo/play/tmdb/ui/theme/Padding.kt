package com.mambo.play.tmdb.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 1:40 pm
 */

// <editor-fold desc="values">
private val PaddingExtraLarge = 24.dp
private val PaddingLarge = 16.dp
private val PaddingMedium = 8.dp
private val PaddingSmall = 4.dp
private val PaddingExtraSmall = 2.dp
// <editor-fold>

// <editor-fold desc="modifier extensions">
fun Modifier.paddingExtraLarge() = padding(PaddingExtraLarge)
fun Modifier.paddingLarge() = padding(PaddingLarge)
fun Modifier.paddingMedium() = padding(PaddingMedium)
fun Modifier.paddingSmall() = padding(PaddingSmall)
fun Modifier.paddingExtraSmall() = padding(PaddingExtraSmall)
// <editor-fold>

data class FlickPadding(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

val MaterialTheme.padding: FlickPadding
    @Composable
    @ReadOnlyComposable
    get() = FlickPadding(
        extraSmall = PaddingExtraSmall,
        small = PaddingSmall,
        medium = PaddingMedium,
        large = PaddingLarge,
        extraLarge = PaddingExtraLarge,
    )