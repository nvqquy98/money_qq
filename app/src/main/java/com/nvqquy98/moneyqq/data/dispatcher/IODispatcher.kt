package com.nvqquy98.moneyqq.data.dispatcher

import com.nvqquy98.moneyqq.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IODispatcher : DispatcherProvider {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}
