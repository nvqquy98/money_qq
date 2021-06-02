package com.nvqquy98.moneyqq.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nvqquy98.moneyqq.domain.usecase.base.BaseInput
import com.nvqquy98.moneyqq.domain.usecase.base.BaseUseCase
import com.nvqquy98.moneyqq.domain.usecase.base.EmptyInput
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {
    val exception = MutableLiveData<Throwable>()
    val isLoading = MutableLiveData<Boolean>()
    private var loadingCount = 0

    val scope = viewModelScope.plus(CoroutineExceptionHandler { _, throwable ->
        exception.postValue(throwable)
    })

    fun <I : BaseInput, O> launchUseCase(
        baseUseCase: BaseUseCase<I, O>,
        input: I = EmptyInput() as I,
        showLoading: Boolean = true,
        showError: Boolean = true,
        onSubscribe: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((O) -> Unit)? = null
    ): Job {
        return scope.launch {
            baseUseCase.execute(input) {
                onSubscribe {
                    onSubscribe?.invoke()
                    if (showLoading) {
                        showLoading()
                    }
                }
                onSuccess {
                    onSuccess?.invoke(it)
                    if (showLoading) {
                        hideLoading()
                    }
                }
                onError {
                    onError?.invoke(it)
                    if (showLoading) {
                        hideLoading()
                    }
                    if (showError) {
                        exception.postValue(it)
                    }
                }

            }
        }
    }

    open fun showLoading() {
        loadingCount++
        isLoading.postValue(true)
    }

    open fun hideLoading() {
        loadingCount--
        if (loadingCount <= 0) isLoading.postValue(false)
    }
}
