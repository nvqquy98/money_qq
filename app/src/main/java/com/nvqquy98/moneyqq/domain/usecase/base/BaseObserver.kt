package com.nvqquy98.moneyqq.domain.usecase.base

open class BaseObserver<Output> {
    private var onSubscribe: (() -> Unit)? = null
    private var onSuccess: ((Output) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null

    fun onSubscribe(block: () -> Unit) {
        onSubscribe = block
    }

    fun onSuccess(block: (Output) -> Unit) {
        onSuccess = block
    }

    fun onError(block: (Throwable) -> Unit) {
        onError = block
    }

    operator fun invoke() = onSubscribe?.invoke()
    operator fun invoke(output: Output) = onSuccess?.invoke(output)
    operator fun invoke(throwable: Throwable) = onError?.invoke(throwable)
}
