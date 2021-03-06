package com.nvqquy98.moneyqq.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nvqquy98.moneyqq.BR
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : Fragment() {
    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    lateinit var binding: B

    val scope = lifecycleScope.plus(CoroutineExceptionHandler { _, _ -> })

    val baseActivity by lazy {
        activity as BaseActivity<*, *>
    }


    private var onDestinationChangedListener: OnDestinationChangedListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setVariable(BR.viewModel, viewModel)

            executePendingBindings()
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        }
        initDataAndViews()
        handleEvents()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        onDestinationChangedListener?.onFragmentDetach(this)
        super.onDestroy()
    }

    open fun initDataAndViews() {}
    open fun handleEvents() {}
    open fun observe() {}
}
