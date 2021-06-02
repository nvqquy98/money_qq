package com.nvqquy98.moneyqq.domain.usecase.base

import kotlinx.coroutines.withContext

abstract class BaseNonFlowUseCase<Input : BaseInput, Output> : BaseUseCase<Input, Output>() {
    abstract override suspend fun buildUseCase(input: Input): Output

    override suspend fun execute(input: Input, block: BaseObserver<Output>.() -> Unit) {
        val response = BaseObserver<Output>().apply { block() }
        response()
        try {
            val result = withContext(dispatchersProvider.dispatcher) {
                buildUseCase(input)
            }
            response(result)
        } catch (e: Throwable) {
            response(e)
        }
    }
}
