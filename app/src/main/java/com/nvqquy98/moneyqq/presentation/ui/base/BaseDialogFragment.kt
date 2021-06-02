package com.nvqquy98.moneyqq.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.BR
import com.nvqquy98.moneyqq.mainapp.MainViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseDialogFragment<B : ViewDataBinding, V : BaseViewModel> :
    AppCompatDialogFragment() {
    protected open val width: Int
        get() = requireContext().resources.getDimensionPixelSize(R.dimen.dp_315)

    protected open val height: Int
        get() = WindowManager.LayoutParams.WRAP_CONTENT

    @get:LayoutRes
    abstract val layoutId: Int
    abstract val viewModel: V
    val mainViewModel: MainViewModel by sharedViewModel()
    lateinit var binding: B

    val scope = lifecycleScope.plus(CoroutineExceptionHandler { _, throwable ->
    })

    val baseActivity by lazy {
        activity as BaseActivity<*, *>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseDialogFragment.viewLifecycleOwner
        }

        dialog?.setCanceledOnTouchOutside(false)
        isCancelable = true
        dialog?.window?.run {
            setLayout(width, height)
            setBackgroundDrawable(null)
        }
        initDataAndViews()
        handleEvents()
        observe()
    }

    fun forceDismiss() {
        if (isVisible) {
            dismissAllowingStateLoss()
        }
    }

    open fun initDataAndViews() {}
    open fun handleEvents() {}
    open fun observe() {}
}