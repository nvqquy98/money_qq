package com.nvqquy98.moneyqq.domain.usecase.base

@Suppress("UNCHECKED_CAST")
abstract class BaseDirectUseCase<in I : BaseInput, O> {

    abstract fun buildUseCase(input: I): O

    operator fun invoke(input: I = EmptyInput() as I): O {
        return buildUseCase(input)
    }
}