package com.mambo.play.tmdb.ui.screens.helpers

import cafe.adriel.voyager.core.model.StateScreenModel

/**
 * @project : Nekos
 * @author  : mambo
 * @email   : mambobryan@gmail.com
 * @date    : Sun 24 December 2023
 * @time    : 11:37 am
 */

open class StatefulScreenModel<T>(value: T) : StateScreenModel<T>(initialState = value) {
    fun update(block: T.() -> T) {
        mutableState.value = mutableState.value.block()
    }

    fun update(value: T) {
        mutableState.value = value
    }
}