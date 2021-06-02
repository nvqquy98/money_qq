package com.nvqquy98.moneyqq.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val dispatcher : CoroutineDispatcher
}
