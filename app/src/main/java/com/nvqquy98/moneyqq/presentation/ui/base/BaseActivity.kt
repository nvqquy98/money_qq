package com.nvqquy98.moneyqq.presentation.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.nvqquy98.moneyqq.BR
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {
    @get:LayoutRes
    abstract val layoutID: Int

    abstract val viewModel: V

    lateinit var binding: B

    val scope = lifecycleScope.plus(CoroutineExceptionHandler { _, _ ->
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutID)
        binding.apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
        initDataAndViews()
        handleEvents()
        observer()
    }

    open fun initDataAndViews() {}
    open fun handleEvents() {}
    open fun observer() {}
}
