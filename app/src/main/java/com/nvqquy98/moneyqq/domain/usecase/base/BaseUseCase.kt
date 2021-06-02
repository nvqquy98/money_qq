package com.nvqquy98.moneyqq.domain.usecase.base

import com.nvqquy98.moneyqq.domain.dispatcher.DispatcherProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

abstract class BaseUseCase<Input : BaseInput, Output> : KoinComponent {
    open val dispatchersProvider = get<DispatcherProvider>(named("io"))
    abstract suspend fun buildUseCase(input: Input): Any?
    abstract suspend fun execute(input: Input, block: BaseObserver<Output>.() -> Unit)
}
