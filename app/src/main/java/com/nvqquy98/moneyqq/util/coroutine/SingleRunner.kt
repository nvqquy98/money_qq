package com.nvqquy98.moneyqq.util.coroutine

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SingleRunner {
    private val mutex = Mutex()

    suspend fun <T> afterPrevious(block: suspend () -> T): T {
        mutex.withLock {
            return block()
        }
    }
}
