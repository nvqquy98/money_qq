package com.nvqquy98.moneyqq.util.coroutine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect

fun <T, R> Flow<T>.zipWithContext(transform: (T?, T?) -> R): Flow<R> = flow {
    var prev: T? = null
    collect { value ->
        emit(transform(prev, value))
        prev = value
    }
}

