package com.nvqquy98.moneyqq.domain.usecase.base

import com.nvqquy98.moneyqq.util.coroutine.ControlledRunner

abstract class BaseCancelableUseCase<Input : BaseInput, Output> :
    BaseNonFlowUseCase<Input, Output>() {
    private val controlledRunner = ControlledRunner<Unit>()

    override suspend fun execute(input: Input, block: BaseObserver<Output>.() -> Unit) {
        controlledRunner.cancelPreviousThenRun {
            super.execute(input, block)
        }
    }
}
