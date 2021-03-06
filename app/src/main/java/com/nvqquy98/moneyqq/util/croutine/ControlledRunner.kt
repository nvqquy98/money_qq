package com.nvqquy98.moneyqq.util.croutine

import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineStart.LAZY
import java.util.concurrent.atomic.AtomicReference

class ControlledRunner<T> {
    private val activeTask = AtomicReference<Deferred<T>?>(null)

    suspend fun cancelPreviousThenRun(block: suspend () -> T): T {
        activeTask.get()?.cancelAndJoin()
        return coroutineScope {
            val newTask = async(start = LAZY) {
                block()
            }
            newTask.invokeOnCompletion {
                activeTask.compareAndSet(newTask, null)
            }
            val result: T
            while (true) {
                if (!activeTask.compareAndSet(null, newTask)) {
                    activeTask.get()?.cancelAndJoin()
                    yield()
                } else {
                    result = newTask.await()
                    break
                }
            }
            result
        }
    }
}
